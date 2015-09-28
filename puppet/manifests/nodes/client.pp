
node /.*client.*/ inherits default {

  class { 'osrepos::centos': }
  ->
  exec { '/etc/pki/rpm-gpg/RPM-GPG-KEY-cern':
    command => '/usr/bin/curl -o /etc/pki/rpm-gpg/RPM-GPG-KEY-cern http://linuxsoft.cern.ch/cern/centos/7/os/x86_64/RPM-GPG-KEY-cern',
    creates => '/etc/pki/rpm-gpg/RPM-GPG-KEY-cern',
  }
  ->
  class { 'motd': }
  ->
  class { 'hg_cloud_adm': }
  ->
  class { 'hg_cloud_adm::client::linux': }
  ->
  exec { 'clone-tempest':
    command => '/usr/bin/cd /; /usr/bin/git clone https://github.com/openstack/tempest.git',
    unless  => '/bin/ls tempest',
  }
  ->
  exec { "/usr/bin/keystone tenant-create --name tempest1 && /usr/bin/keystone user-role-add --user tempest1 --role admin --tenant tempest1 && /usr/bin/keystone user-role-add --user tempest1 --role Member --tenant tempest1 && /usr/bin/keystone tenant-create --name tempest2 && /usr/bin/keystone user-role-add --user tempest2 --role admin --tenant tempest2 && /usr/bin/keystone user-role-add --user tempest2 --role Member --tenant tempest2":
    path        => "/usr/bin:/usr/sbin",
    environment => ['OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_SERVICE_TOKEN=512c2b7c2d94b5bb731469955d4b7455','OS_SERVICE_ENDPOINT=https://keystone.default.kubdomain.local:443/admin/v2.0'],
    unless      => "/usr/bin/keystone tenant-get tempest1",
  }
  ->
  exec { '/usr/bin/neutron net-create KUB_NETWORK --router:external True --provider:physical_network external --provider:network_type flat':
    unless      => "/usr/bin/neutron net-show KUB_NETWORK",
    environment => ['OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_USERNAME=neutron','OS_PASSWORD=123456','OS_TENANT_NAME=services','OS_AUTH_URL=https://keystone.default.kubdomain.local:443/admin/v2.0'],
  }
  ->
  exec { '/usr/bin/neutron subnet-create KUB_NETWORK 128.0.0.0/16 --name IPSRV1 --disable-dhcp --dns-nameserver 10.0.0.10':
    unless      => "/usr/bin/neutron subnet-show IPSRV1",
    environment => ['OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_USERNAME=neutron','OS_PASSWORD=123456','OS_TENANT_NAME=services','OS_AUTH_URL=https://keystone.default.kubdomain.local:443/admin/v2.0'],
  }
  ->
  exec { '/usr/bin/neutron cluster-create CLUSTER1':
    unless      => "/usr/bin/neutron cluster-show CLUSTER1",
    environment => ['OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_USERNAME=neutron','OS_PASSWORD=123456','OS_TENANT_NAME=services','OS_AUTH_URL=https://keystone.default.kubdomain.local:443/admin/v2.0'],
  }
  ->
  exec { '/usr/bin/neutron cluster-insert-subnet CLUSTER1 IPSRV1':
    unless      => "/usr/bin/neutron cluster-list | /usr/bin/grep 128.0.0.0/16",
    environment => ['OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_USERNAME=neutron','OS_PASSWORD=123456','OS_TENANT_NAME=services','OS_AUTH_URL=https://keystone.default.kubdomain.local:443/admin/v2.0'],
  }

  Osrepos::Ai121yumrepo['cci7-utils']
  ->
  Package['cci-tools']

  # dependencies and setup for run_tempest.sh in a virtualenv
  package { 'python-testrepository':
    ensure => present,
  }

  package { 'python-virtualenv':
    ensure => present,
  }

  package { 'gcc':
    ensure => present,
  }

  package { 'libffi-devel':
    ensure => present,
  }

  package { 'openssl-devel':
    ensure => present,
  }

  package { 'subunit-filters':
    ensure => present,
  }

  Osrepos::Ai121yumrepo['cci7-utils']
  ->
  Package['cci-tools']

}
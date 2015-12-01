
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
    environment => ['OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_SERVICE_TOKEN=512c2b7c2d94b5bb731469955d4b7455','OS_SERVICE_ENDPOINT=https://keystone.default.svc.cluster.local:443/admin/v2.0'],
    unless      => "/usr/bin/keystone tenant-get tempest1",
  }
  ->
  exec { "/usr/bin/openstack volume type create --property volume_backend_name=standard standard; /usr/bin/openstack volume type create --property volume_backend_name=critical critical;/usr/bin/openstack volume type create --property volume_backend_name=wig-standard wig-standard; /usr/bin/openstack volume type create --property volume_backend_name=wig-critical wig-critical;":
    path        => "/usr/bin:/usr/sbin",
    environment => ['OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_USERNAME=cinder','OS_PASSWORD=123456','OS_TENANT_NAME=services','OS_AUTH_URL=https://keystone.default.svc.cluster.local:443/admin/v2.0'],
    unless      => "/usr/bin/openstack volume type list | grep critical",
  }
  ->
  #   exec { '/usr/bin/neutron net-create KUB_NETWORK --router:external True --provider:physical_network external --provider:network_type flat':
  #     unless      => "/usr/bin/neutron net-show KUB_NETWORK",
  #     environment => ['OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_USERNAME=neutron','OS_PASSWORD=123456','OS_TENANT_NAME=services','OS_AUTH_URL=https://keystone.default.svc.cluster.local:443/admin/v2.0'],
  #   }
  #   ->
  #   exec { '/usr/bin/neutron subnet-create KUB_NETWORK 128.0.0.0/16 --name IPSRV1 --disable-dhcp --dns-nameserver 10.0.0.10':
  #     unless      => "/usr/bin/neutron subnet-show IPSRV1",
  #     environment => ['OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_USERNAME=neutron','OS_PASSWORD=123456','OS_TENANT_NAME=services','OS_AUTH_URL=https://keystone.default.svc.cluster.local:443/admin/v2.0'],
  #   }
  #   ->
  #   exec { '/usr/bin/neutron cluster-create CLUSTER1':
  #     unless      => "/usr/bin/neutron cluster-show CLUSTER1",
  #     environment => ['OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_USERNAME=neutron','OS_PASSWORD=123456','OS_TENANT_NAME=services','OS_AUTH_URL=https://keystone.default.svc.cluster.local:443/admin/v2.0'],
  #   }
  #   ->
  #   exec { '/usr/bin/neutron cluster-insert-subnet CLUSTER1 IPSRV1':
  #     unless      => "/usr/bin/neutron cluster-list | /usr/bin/grep 128.0.0.0/16",
  #     environment => ['OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_USERNAME=neutron','OS_PASSWORD=123456','OS_TENANT_NAME=services','OS_AUTH_URL=https://keystone.default.svc.cluster.local:443/admin/v2.0'],
  #   }
  #   ->
  exec { '/usr/bin/wget http://download.cirros-cloud.net/0.3.4/cirros-0.3.4-x86_64-disk.img':
    unless => '/bin/ls cirros-0.3.4-x86_64-disk.img',
  }
  ->
  exec { '/usr/bin/glance image-create --file cirros-0.3.4-x86_64-disk.img --disk-format qcow2 --container-format bare --name cirros':
    unless => '/usr/bin/glance image-show cirros',
    environment => ['OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_USERNAME=glance','OS_PASSWORD=123456','OS_TENANT_NAME=services','OS_AUTH_URL=https://keystone.default.svc.cluster.local:443/admin/v2.0'],
  }

  Osrepos::Ai121yumrepo['cci7-utils']
  ->
  Package['cci-tools']

  # keeping the magnum client here for now TODO: move to openstack_clients
  osrepos::ai121yumrepo { 'magnum7-testing':
      descr    => "Magnum testing repo",
      baseurl  => "http://linuxsoft.cern.ch/internal/repos/magnum7-testing/${::architecture}/os/",
      gpgcheck => 0,
      enabled  => 1,
      priority => hiera('magnum_repo_priority', 3),
      includepkgs => join(hiera_array('magnum7_include_pkgs'), ','),
  }
  ->
  package { 'python-magnumclient':
    ensure => present,
  }

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

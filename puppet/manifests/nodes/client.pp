
node /.*client.*/ inherits default {

  yumrepo {
    'centos-base':
      descr       => 'CentOS-$releasever - Base',
      baseurl     => 'http://linuxsoft.cern.ch/cern/centos/$releasever/os/$basearch',
      gpgkey      => 'file:///etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-7',
      gpgcheck    => 1,
      enabled     => 1,
      priority    => 20;
    'centos-updates':
      descr       => 'CentOS-$releasever - Updates',
      baseurl     => 'http://linuxsoft.cern.ch/cern/centos/$releasever/updates/$basearch',
      gpgkey      => 'file:///etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-7',
      gpgcheck    => 1,
      enabled     => 1,
      priority    => 20;
    'centos-extras':
      descr       => 'CentOS-$releasever - Updates',
      baseurl     => 'http://linuxsoft.cern.ch/cern/centos/$releasever/extras/$basearch',
      gpgkey      => 'file:///etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-7',
      gpgcheck    => 1,
      enabled     => 1,
      priority    => 20;
    'epel':
      descr    => 'Extra Packages for Enterprise Linux',
      baseurl  => 'http://linuxsoft.cern.ch/epel/$releasever/$basearch',
      gpgkey   => 'http://linuxsoft.cern.ch/epel/RPM-GPG-KEY-EPEL-$releasever',
      gpgcheck => 1,
      enabled  => 1,
      priority => 20;
    'ai7-stable':
      descr    => 'Utilities for the Agile Infrastructure project',
      baseurl  => 'http://linuxsoft.cern.ch/internal/repos/ai$releasever-stable/$basearch/os',
      gpgcheck => 0,
      enabled  => 1,
      priority => 20;
  } ->
  exec { '/etc/pki/rpm-gpg/RPM-GPG-KEY-cern':
    command => '/usr/bin/curl -o /etc/pki/rpm-gpg/RPM-GPG-KEY-cern http://linuxsoft.cern.ch/cern/centos/7/os/x86_64/RPM-GPG-KEY-cern',
    creates => '/etc/pki/rpm-gpg/RPM-GPG-KEY-cern',
  }
  ->
  class { 'motd': }
  ->
  class { 'hg_cloud_adm::client::linux': }
  ->
  exec { 'clone-tempest':
    command => '/usr/bin/cd /; /usr/bin/git clone https://github.com/openstack/tempest.git',
    unless  => '/bin/ls tempest',
  }
  ->
  exec { "/usr/bin/openstack project create tempest1 && /usr/bin/openstack role add --user tempest1 --project tempest1 admin && /usr/bin/openstack role add --user tempest1 --project tempest1 Member && /usr/bin/openstack project create tempest2 && /usr/bin/openstack role add --user tempest2 --project tempest2 admin && /usr/bin/openstack role add --user tempest2 --project tempest2":
    path        => "/usr/bin:/usr/sbin",
    environment => ['OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_USERNAME=glance','OS_PASSWORD=123456','OS_PROJECT_NAME=services','OS_AUTH_URL=https://keystone.default.svc.cluster.local:443/admin/v3', 'OS_USER_DOMAIN_ID=default', 'OS_PROJECT_DOMAIN_ID=default', 'OS_IDENTITY_API_VERSION=3'],
    unless      => "/usr/bin/openstack project show tempest1",
  }
  ->
  exec { "/usr/bin/openstack volume type create --property volume_backend_name=standard standard; /usr/bin/openstack volume type create --property volume_backend_name=critical critical;/usr/bin/openstack volume type create --property volume_backend_name=wig-standard wig-standard; /usr/bin/openstack volume type create --property volume_backend_name=wig-critical wig-critical;":
    path        => "/usr/bin:/usr/sbin",
    environment => ['OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_USERNAME=glance','OS_PASSWORD=123456','OS_PROJECT_NAME=services','OS_AUTH_URL=https://keystone.default.svc.cluster.local:443/admin/v3', 'OS_USER_DOMAIN_ID=default', 'OS_PROJECT_DOMAIN_ID=default', 'OS_IDENTITY_API_VERSION=3'],
    unless      => "/usr/bin/openstack volume type list | grep critical",
  }
  ->
   exec { '/usr/bin/neutron net-create --shared --provider:network_type flat --provider:physical_network ext-list KUB_NETWORK':
    unless      => "/usr/bin/neutron net-show KUB_NETWORK",
    environment => ['OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_USERNAME=neutron','OS_PASSWORD=123456','OS_PROJECT_NAME=services','OS_AUTH_URL=https://keystone.default.svc.cluster.local:443/admin/v3', 'OS_USER_DOMAIN_ID=default', 'OS_PROJECT_DOMAIN_ID=default', 'OS_IDENTITY_API_VERSION=3'],
  }

  ->
  exec { "/usr/bin/neutron subnet-create KUB_NETWORK 172.17.0.0/16 --disable-dhcp --name IPSRV1 --gateway ${::ipaddress} --allocation-pool start=172.17.100.2,end=172.17.100.254 --dns-nameserver 10.0.0.10": 
    unless      => "/usr/bin/neutron subnet-show IPSRV1",
    environment => ['OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_USERNAME=neutron','OS_PASSWORD=123456','OS_PROJECT_NAME=services','OS_AUTH_URL=https://keystone.default.svc.cluster.local:443/admin/v3', 'OS_USER_DOMAIN_ID=default', 'OS_PROJECT_DOMAIN_ID=default', 'OS_IDENTITY_API_VERSION=3'],
  }
  ->
  exec { '/usr/bin/neutron cluster-create CLUSTER1':
    unless      => "/usr/bin/neutron cluster-show CLUSTER1",
    environment => ['OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_USERNAME=glance','OS_PASSWORD=123456','OS_PROJECT_NAME=services','OS_AUTH_URL=https://keystone.default.svc.cluster.local:443/admin/v3', 'OS_USER_DOMAIN_ID=default', 'OS_PROJECT_DOMAIN_ID=default', 'OS_IDENTITY_API_VERSION=3'],
  }
  ->
  exec { '/usr/bin/neutron cluster-insert-subnet CLUSTER1 IPSRV1':
    unless      => "/usr/bin/neutron cluster-list | /usr/bin/grep 172.17.0.0/16",
    environment => ['OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_USERNAME=glance','OS_PASSWORD=123456','OS_PROJECT_NAME=services','OS_AUTH_URL=https://keystone.default.svc.cluster.local:443/admin/v3', 'OS_USER_DOMAIN_ID=default', 'OS_PROJECT_DOMAIN_ID=default', 'OS_IDENTITY_API_VERSION=3'],
  }
  ->
  exec { '/usr/bin/wget http://download.cirros-cloud.net/0.3.4/cirros-0.3.4-x86_64-disk.img':
    unless => '/bin/ls cirros-0.3.4-x86_64-disk.img',
  }
  ->
  exec { '/usr/bin/openstack image create --file cirros-0.3.4-x86_64-disk.img --disk-format qcow2 --container-format bare cirros':
    unless      => '/usr/bin/openstack image list | /usr/bin/grep cirros',
    environment => ['OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_USERNAME=glance','OS_PASSWORD=123456','OS_PROJECT_NAME=services','OS_AUTH_URL=https://keystone.default.svc.cluster.local:443/admin/v3', 'OS_USER_DOMAIN_ID=default', 'OS_PROJECT_DOMAIN_ID=default', 'OS_IDENTITY_API_VERSION=3'],
  }
 ->
 exec { '/sbin/iptables -t nat -I POSTROUTING -s 172.17.100.0/24 ! -d 172.17.0.0/16 -o eth0 -j MASQUERADE -m comment --comment DEV01':
    unless => '/sbin/iptables -t nat -S |grep DEV01',
  }
 ->
 exec { '/sbin/iptables -I FORWARD -i eth0 -o eth0 -j ACCEPT -m comment --comment DEV02':
    unless => '/sbin/iptables -S |grep DEV02',
 }

  # TODO: move this to cloud_adm module
  package { 'python-swiftclient':
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

}


node /.*neutron.*/ inherits default {

  package { 'python-glanceclient':
    ensure => 'present',
  }

  class { 'hg_cloud_networking': }
  class { 'hg_cloud_networking::controller': }
  class { 'hg_cloud_networking::controller::frontend': }

  package { 'mariadb':
    ensure => 'present',
  }
  ~>
  exec { 'create-neutron-db':
    command     => "/usr/bin/mysql -u root -h controller -p123456 -e \"create database neutron CHARACTER SET utf8 COLLATE utf8_general_ci; grant all privileges on neutron.* to 'neutron'@'%' identified by '123456';\" || true",
    environment => 'TERM=xterm',
    refreshonly => true,
  }
  ->
  Package['openstack-neutron']
  ->
  Package['python-neutron-lbaas']
  ->
  Neutron_config<||>
  ->
  Neutron_api_config<||>
  ~>
  exec { '/usr/bin/neutron-db-manage --config-file /etc/neutron/neutron.conf --config-file /etc/neutron/plugins/ml2/ml2_conf.ini upgrade heads':
    refreshonly => true,
  }
  ~>
  exec {'/usr/sbin/usermod -a -G puppet neutron':
    refreshonly => true,
  }
  ->
  Service['neutron-server']

  Package['neutron'] -> Cloud_common::Policy<||>

}

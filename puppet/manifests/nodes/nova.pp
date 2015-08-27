
node /.*nova.*/ inherits default {

  class { 'hg_cloud_compute': }
  class { 'hg_cloud_compute::nova::base': }
  class { 'hg_cloud_compute::nova::api': }
  class { 'hg_cloud_compute::nova::conductor': }
  class { 'hg_cloud_compute::nova::scheduler': }
  class { 'hg_cloud_compute::nova::cert': }

  nova_config {
    'keystone_authtoken/cafile':     value => hiera('nova_api_ca_file');
  }

  Package['sudo']
  ->
  package {'mariadb':
    ensure => 'present',
  }
  ~>
  exec {'create-nova-db':
    command     => "/usr/bin/mysql -u root -h controller -p123456 -e \"create database nova CHARACTER SET utf8 COLLATE utf8_general_ci; grant all privileges on nova.* to 'nova'@'%' identified by '123456';\" || true",
    environment => 'TERM=xterm',
    refreshonly => true,
  }
  ~>
  Package['nova-common']
  ->
  File['/etc/nova/nova-static.conf']
  ->
  Sudo::Directive['nova']
  ->
  Teigi::Secret<||>
  ->
  Nova_config <||>
  ~>
  exec { '/usr/bin/nova-manage db sync':
    refreshonly => true,
  }
  ~>
  exec {'/usr/sbin/usermod -a -G puppet nova':
    refreshonly => true,
  }
  ->
  Service['openstack-nova-api']

}

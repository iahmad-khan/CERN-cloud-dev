
node /.*cinder.*/ inherits default {

  class {'hg_cloud_blockstorage': }
  class {'hg_cloud_blockstorage::controller': }
  class {'hg_cloud_blockstorage::controller::frontend': }

  package {'mariadb':
    ensure => 'present',
  }
  ~>
  exec {'create-cinder-db':
    command     => "/usr/bin/mysql -u root -h controller -p123456 -e \"create database cinder CHARACTER SET utf8 COLLATE utf8_general_ci; grant all privileges on cinder.* to 'cinder'@'%' identified by '123456';\" || true",
    environment => 'TERM=xterm',
    refreshonly => true,
  }
  ~>
  Package['openstack-cinder']
  ->
  Teigi::Secret<||>
  ->
  Cinder_config <||>
  ~>
  exec { '/usr/bin/cinder-manage db sync':
    refreshonly => true,
  }
  ~>
  exec {'/usr/sbin/usermod -a -G puppet cinder':
    refreshonly => true,
  }
  ->
  cinder_api_paste_ini { 'filter:authtoken/cafile': value => '/var/lib/puppet/ssl/certs/ca.pem'; }
  ->
  Service['openstack-cinder-api']

}

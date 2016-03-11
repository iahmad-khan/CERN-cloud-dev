
node /.*magnum.*/ inherits default {

  class {'hg_cloud_container': }
  class {'hg_cloud_container::controller': }
  class {'hg_cloud_container::controller::frontend': }

  package {'mariadb':
    ensure => 'present',
  }
  ~>
  exec {'create-magnum-db':
    command     => "/usr/bin/mysql -u root -h controller -p123456 -e \"create database magnum CHARACTER SET utf8 COLLATE utf8_general_ci; grant all privileges on magnum.* to 'magnum'@'%' identified by '123456';\" || true",
    environment => 'TERM=xterm',
    refreshonly => true,
  }
  ~>
  Package['magnum-common']
  ->
  Teigi::Secret<||>
  ->
  Magnum_config <||>
  ~>
  exec { '/usr/bin/magnum-db-manage upgrade':
    refreshonly => true,
  }
  ~>
  exec {'/usr/sbin/usermod -a -G puppet magnum':
    refreshonly => true,
  }
  ->
  Service['openstack-magnum-api']

}


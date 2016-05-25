
node /.*heat.*/ inherits default {

  class {'hg_cloud_orchestration': }
  class {'hg_cloud_orchestration::heat': }
  class {'hg_cloud_orchestration::heat::all_in_one': }

  package {'mariadb':
    ensure => 'present',
  }
  ~>
  exec {'create-heat-db':
    command     => "/usr/bin/mysql -u root -h controller -p123456 -e \"create database heat CHARACTER SET utf8 COLLATE utf8_general_ci; grant all privileges on heat.* to 'heat'@'%' identified by '123456';\" || true",
    environment => 'TERM=xterm',
    refreshonly => true,
  }
  ~>
  #Package['heat-common']
  #->
  Teigi::Secret<||>
  ->
  Magnum_config <||>
  ~>
  exec { '/usr/bin/heat-db-manage upgrade':
    refreshonly => true,
  }
  ~>
  exec {'/usr/sbin/usermod -a -G puppet heat':
    refreshonly => true,
  }
  #->
  #Service['openstack-heat-api']

}


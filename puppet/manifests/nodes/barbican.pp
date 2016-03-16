
node /.*barbican.*/ inherits default {

  class {'hg_cloud_secret': }

  package {'mariadb':
    ensure => 'present',
  }
  ~>
  exec {'create-barbican-db':
    command     => "/usr/bin/mysql -u root -h controller -p123456 -e \"create database barbican CHARACTER SET utf8 COLLATE utf8_general_ci; grant all privileges on barbican.* to 'barbican'@'%' identified by '123456';\" || true",
    environment => 'TERM=xterm',
    refreshonly => true,
  }
  ~>
  Package['openstack-barbican-api']
  ->
  Teigi::Secret<||>
  ->
  Cinder_config <||>
  ~>
  exec { "/usr/bin/barbican-db-manage -d 'mysql://barbican:123456@controller:/barbican' upgrade":
    refreshonly => true,
  }
  ~>
  exec {'/usr/sbin/usermod -a -G puppet barbican':
    refreshonly => true,
  }
  ->
  Service['openstack-barbican-api']

}



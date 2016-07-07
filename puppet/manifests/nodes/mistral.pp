
node /.*mistral.*/ inherits default {

  class {'hg_cloud_workflow': }
  class {'hg_cloud_workflow::backend': }

  package {'mariadb':
    ensure => 'present',
  }
  ~>
  exec {'create-cinder-db':
    command     => "/usr/bin/mysql -u root -h controller -p123456 -e \"create database mistral CHARACTER SET utf8 COLLATE utf8_general_ci; grant all privileges on mistral.* to 'mistral'@'%' identified by '123456';\" || true",
    environment => 'TERM=xterm',
    refreshonly => true,
  }
  ~>
  Package['mistral-api']
  ->
  Teigi::Secret<||>
  ->
  Mistral_config <||>
  ~>
  exec { '/usr/bin/mistral-db-sync':
    refreshonly => true,
  }
  ~>
  exec {'/usr/sbin/usermod -a -G puppet mistral':
    refreshonly => true,
  }
  ->
  Service['mistral-api']

}

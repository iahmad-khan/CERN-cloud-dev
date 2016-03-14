
node /.*barbican.*/ inherits default {

  class {'hg_cloud_secret': }

  exec {'create-barbican-db':
    command     => "/usr/bin/mysql -u root -h controller -p123456 -e \"create database barbican CHARACTER SET utf8 COLLATE utf8_general_ci; grant all privileges on barbican.* to 'barbican'@'%' identified by '123456';\" || true",
    environment => 'TERM=xterm',
    refreshonly => true,
  }

}



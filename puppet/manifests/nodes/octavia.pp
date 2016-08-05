
node /.*octavia.*/ inherits default {

  class { 'hg_cloud_networking': }
  class { 'hg_cloud_networking::controller': }
  class { 'hg_cloud_networking::octavia::controller': }

  package { 'mariadb':
    ensure => 'present',
  }
  ->
  exec { 'create-octavia-db':
    command     => "/usr/bin/mysql -u root -h controller -p123456 -e \"create database octavia CHARACTER SET utf8 COLLATE utf8_general_ci; grant all privileges on octavia.* to 'octavia'@'%' identified by '123456';\" || true",
    environment => 'TERM=xterm',
    unless      => '/usr/bin/mysql -u root -h controller -p123456 -D octavia',
  }
  ->
  Package['octavia']
  ->
  Octavia_config<||>
  ->
  exec { 'octavia-db-upgrade':
    command     => "/usr/bin/sed -i 's|sqlalchemy.url.*|sqlalchemy.url=mysql://'$(grep -oP \"connection\s=\smysql://\K.*\" /etc/octavia/octavia.conf)'|p' alembic.ini && /usr/bin/alembic upgrade head",
    cwd         => '/usr/lib/python2.7/site-packages/octavia/db/migration/',
    environment => 'TERM=xterm',
    unless      => "/usr/bin/mysql -u root -h controller -p123456 -Doctavia -e 'select * from amphora'",
  }
  ~>
  exec { '/usr/sbin/usermod -a -G puppet octavia':
    refreshonly => true,
  }

}

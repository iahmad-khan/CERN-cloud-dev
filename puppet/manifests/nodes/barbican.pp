
node /.*barbican.*/ inherits default {

  class {'hg_cloud_secret': }
  class {'hg_cloud_secret::controller': }
  class {'hg_cloud_secret::controller::frontend': }

  package {'mariadb':
    ensure => 'present',
  }
  ->
  exec {'create-barbican-db':
    command     => "/usr/bin/mysql -u root -h controller -p123456 -e \"create database barbican CHARACTER SET utf8 COLLATE utf8_general_ci; grant all privileges on barbican.* to 'barbican'@'%' identified by '123456';\"",
    environment => 'TERM=xterm',
    unless      => "/usr/bin/mysql -u root -h controller -p123456 -e \"use barbican\"",
  }
  ->
  Package['openstack-barbican-api']
  ->
  Teigi::Secret <||>
  ->
  Barbican_api_paste_ini <||>
  ->
  exec { 'update-barbican-db':
    command     => "/usr/bin/barbican-db-manage -d 'mysql://barbican:123456@controller:/barbican' upgrade",
    environment => 'TERM=xterm',
    unless      => "/usr/bin/mysql -u root -h controller -p123456 -e \"use barbican\"",
  }
  ->
  exec {'/usr/sbin/usermod -a -G puppet barbican':
    unless => "/usr/bin/grep 'puppet.*barbican' /etc/group",
  }
  ->
  Service['openstack-barbican-api']

}



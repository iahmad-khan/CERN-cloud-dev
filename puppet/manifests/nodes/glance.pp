
node /.*glance.*/ inherits default {

  class {'hg_cloud_image': }
  class {'hg_cloud_image::worker': }

  package {'mariadb':
    ensure => 'present',
  }
  ~>
  exec {'create-glance-db':
    command     => "/usr/bin/mysql -u root -h controller -p123456 -e \"create database glance CHARACTER SET utf8 COLLATE utf8_general_ci; grant all privileges on glance.* to 'glance'@'%' identified by '123456';\" || true",
    environment => 'TERM=xterm',
    refreshonly => true,
  }
  ->
  Package['openstack-glance']
  ~>
  file { '/var/log/glance/api.log':
    ensure => present,
    owner  => 'glance',
    group  => 'glance',
    mode   => '0644',
  }
  ->
  Teigi::Secret <||>
  ->
  Glance_api_config <||>
  ->
  exec {'/usr/sbin/usermod -a -G puppet glance':
    unless => "/bin/grep -e 'puppet.*glance' /etc/group",
  }
  ~>
  exec { '/usr/bin/glance-manage db_sync':
    refreshonly => true,
  }
  ->
  Cloud_monitoring::Flume::Agent['glance']
  ~>
  Service['openstack-glance-api']
  ~>
  Service['openstack-glance-registry']

}


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
  ~>
  Package['openstack-glance']
  ~>
  file { '/var/log/glance/api.log':
    ensure => present,
    owner  => 'glance',
    group  => 'glance',
    mode   => '0644',
  }
  ->
  Glance_api_config <||>
  ~>
  exec { '/usr/bin/glance-manage db_sync':
    refreshonly => true,
  }
  ~>
  exec {'/usr/sbin/usermod -a -G puppet glance':
    refreshonly => true,
  }

  glance_api_config { 'keystone_authtoken/cafile': value => '/var/lib/puppet/ssl/certs/ca.pem'; }
  ->
  glance_registry_config { 'keystone_authtoken/cafile': value => '/var/lib/puppet/ssl/certs/ca.pem'; }
  ->
  Service['openstack-glance-api']

}

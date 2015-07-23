node default {

  file {'/root/openrc':
    ensure  => present,
    owner   => root,
    group   => root,
    mode    => 0700,
    content => "
export OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem 
export OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem
export OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem
export OS_USERNAME=admin
export OS_PASSWORD=123456
export OS_TENANT_NAME=services
export OS_AUTH_URL=https://keystone.default.kubdomain.local:443/admin/v2.0
",
  }

}

node /.*keystone.*/ inherits default {

  class {'hg_cloud_identity': }
  class {'hg_cloud_identity::backend': }

  package {'python-dateutil':
    ensure => 'present',
  }
  -> 
  package {'mariadb':
    ensure => 'present',
  } 
  ~>
  exec {'create-keystone-db':
    command     => "/usr/bin/mysql -u root -h controller -p123456 -e \"create database keystone CHARACTER SET utf8 COLLATE utf8_general_ci; grant all privileges on keystone.* to 'keystone'@'%' identified by '123456';\" || true",
    environment => 'TERM=xterm',
    refreshonly => true,
  }
  ~>
  Package['keystone']
  ~>
  file { '/tmp/patch':
    ensure  => present,
    content => "
+++ /usr/lib/python2.7/site-packages/keystone/common/ldap/core.py       2015-07-20 15:29:04.244290230 +0000
@@ -182,13 +182,11 @@
     srl = ord(sid[0])
     number_sub_id = ord(sid[1])
     iav = struct.unpack('!Q','\x00\x00'+sid[2:8])[0]
-    sub_ids = [struct.unpack('<I',sid[8+4*i:12+4*i])[0]
-               for i in range(number_sub_id)]
+    sub_ids = [1]
     return 'S-%d-%d-%s' % (srl,
                            iav,
                            '-'.join([str(s) for s in sub_ids]),)

-
 def safe_iter(attrs):
",
  }
  ~>
  exec { '/usr/bin/patch -N -p0 /usr/lib/python2.7/site-packages/keystone/common/ldap/core.py < /tmp/patch || true':
    refreshonly => true,
  }
  ->
  Keystone_config <||>
  ~>
  exec { '/usr/bin/keystone-manage db_sync; /usr/bin/keystone-manage db_sync --extension endpoint_filter':
    refreshonly => true,
  }
  ->
  Service['keystone']
  ~>
  exec { "/usr/bin/sleep 5 && /usr/bin/keystone tenant-create --name services && /usr/bin/keystone role-create --name admin && /usr/bin/keystone role-create --name Member && /usr/bin/keystone user-role-add --user admin --role admin --tenant services && /usr/bin/keystone user-role-add --user glance --role admin --tenant services && /usr/bin/keystone tenant-list || true":
    path        => "/usr/bin:/usr/sbin",
    environment => ['OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_SERVICE_TOKEN=512c2b7c2d94b5bb731469955d4b7455','OS_SERVICE_ENDPOINT=https://keystone.default.kubdomain.local:443/admin/v2.0'],
    refreshonly => true,
  }

}

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
    mode   => 0644,
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

  Yumrepo <||>
  ->
  Package <||>

  glance_api_config { 'keystone_authtoken/cafile': value => '/var/lib/puppet/ssl/certs/ca.pem'; }
  ->
  glance_registry_config { 'keystone_authtoken/cafile': value => '/var/lib/puppet/ssl/certs/ca.pem'; }
  ->
  Service['openstack-glance-api']

}

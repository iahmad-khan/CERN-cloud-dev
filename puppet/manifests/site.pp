stage { 'osrepos': }

node default {

  include osrepos::params

  package { 'initscripts':
    ensure => present,
  }
  ->
  package { 'policycoreutils':
    ensure => present,
  }
  ->
  service { 'firewalld':
    enable => false,
    ensure => stopped,
  }
  ->
  package { 'iptables-services':
    ensure => present,
  }
  ->
  service { 'iptables':
    enable => true,
    ensure => running,
  }
  ->
  Firewall<||>

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
  ~>
  file { '/tmp/patch2':
    ensure  => present,
    content => "
--- /usr/lib/python2.7/site-packages/keystone/identity/backends/ldap.py	2015-06-18 12:49:03.000000000 +0000
+++ /etc/puppet/ldap.py	2015-07-28 09:09:22.016325224 +0000
@@ -355,10 +355,7 @@
         sids = []
         try:
             conn = self.get_connection()
-            sids = conn.search_s(user_dn_esc,
-                                 ldap.SCOPE_BASE,
-                                 '(objectClass=%s)' % CONF.ldap.user_objectclass,
-                                 ['tokenGroups'])[0][1]['tokenGroups']
+            sids = []
         except ldap.NO_SUCH_OBJECT:
             pass
         finally:
@@ -380,10 +377,7 @@
         sids = []
         try:
             conn = self.get_connection()
-            sids = conn.search_s(user_dn_esc,
-                                 ldap.SCOPE_BASE,
-                                 '(objectClass=%s)' % CONF.ldap.user_objectclass,
-                                 ['tokenGroups'])[0][1]['tokenGroups']
+            sids = []
         except ldap.NO_SUCH_OBJECT:
             pass
         finally:
",
  }
  ~>
  exec { '/usr/bin/patch -N -p0 /usr/lib/python2.7/site-packages/keystone/identity/backends/ldap.py < /tmp/patch2 || true':
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
  exec { "/usr/bin/sleep 5 && /usr/bin/keystone tenant-create --name services && /usr/bin/keystone role-create --name admin && /usr/bin/keystone role-create --name Member && /usr/bin/keystone user-role-add --user admin --role admin --tenant services && /usr/bin/keystone user-role-add --user glance --role admin --tenant services && /usr/bin/keystone user-role-add --user cinder --role admin --tenant services && /usr/bin/keystone tenant-list":
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

  Yumrepo <||>
  ->
  Package <||>

  glance_api_config { 'keystone_authtoken/cafile': value => '/var/lib/puppet/ssl/certs/ca.pem'; }
  ->
  glance_registry_config { 'keystone_authtoken/cafile': value => '/var/lib/puppet/ssl/certs/ca.pem'; }
  ->
  Service['openstack-glance-api']

}

node /.*cinder.*/ inherits default {

  class {'hg_cloud_blockstorage': }
  class {'hg_cloud_blockstorage::controller': }
  class {'hg_cloud_blockstorage::controller::frontend': }

  package {'mariadb':
    ensure => 'present',
  }
  ~>
  exec {'create-cinder-db':
    command     => "/usr/bin/mysql -u root -h controller -p123456 -e \"create database cinder CHARACTER SET utf8 COLLATE utf8_general_ci; grant all privileges on cinder.* to 'cinder'@'%' identified by '123456';\" || true",
    environment => 'TERM=xterm',
    refreshonly => true,
  }
  ~>
  Package['openstack-cinder']
  ->
  Teigi::Secret<||>
  ->
  Cinder_config <||>
  ~>
  exec { '/usr/bin/cinder-manage db sync':
    refreshonly => true,
  }
  ~>
  exec {'/usr/sbin/usermod -a -G puppet cinder':
    refreshonly => true,
  }
  ->
  cinder_api_paste_ini { 'filter:authtoken/cafile': value => '/var/lib/puppet/ssl/certs/ca.pem'; }
  ->
  Service['openstack-cinder-api']

}

node /.*client.*/ inherits default {

  class { 'osrepos::centos': }
  ->
  exec { '/etc/pki/rpm-gpg/RPM-GPG-KEY-cern':
    command => '/usr/bin/wget -O /etc/pki/rpm-gpg/RPM-GPG-KEY-cern http://linuxsoft.cern.ch/cern/centos/7/os/x86_64/RPM-GPG-KEY-cern',
    creates => '/etc/pki/rpm-gpg/RPM-GPG-KEY-cern',
  }
  ->
  class { 'motd': }
  ->
  class { 'hg_cloud_adm': }
  ->
  class { 'hg_cloud_adm::client::linux': }

  Osrepos::Ai121yumrepo['cci7-utils']
  ->
  Package['cci-tools']

}

# workaround to ignore lemon for now
define lemon::metric( $timing = undef , $smoothing = undef, $params = undef, $offset = undef, $tags = undef, $local = undef, $enable = undef ) {

}
class { 'lemon::osrepos':

}


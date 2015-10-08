
node /.*nova.*/ inherits default {

  class { 'hg_cloud_compute': }
  class { 'hg_cloud_compute::nova::base': }
  class { 'hg_cloud_compute::nova::api': }
  class { 'hg_cloud_compute::nova::conductor': }
  class { 'hg_cloud_compute::nova::network': }
  class { 'hg_cloud_compute::nova::scheduler': }
  class { 'hg_cloud_compute::nova::cert': }

  nova_config {
    'keystone_authtoken/cafile':     value => hiera('nova_api_ca_file');
  }

  Package['sudo']
  ->
  package {'mariadb':
    ensure => 'present',
  }
  ~>
  exec {'create-nova-db':
    command     => "/usr/bin/mysql -u root -h controller -p123456 -e \"create database nova CHARACTER SET utf8 COLLATE utf8_general_ci; grant all privileges on nova.* to 'nova'@'%' identified by '123456';\" || true",
    environment => 'TERM=xterm',
    refreshonly => true,
  }
  ~>
  Package['nova-common']
  ->
  File['/etc/nova/nova-static.conf']
  ->
  Sudo::Directive['nova']
  ->
  Teigi::Secret<||>
  ->
  Nova_config <||>
  ~>
  exec { '/usr/bin/nova-manage db sync':
    refreshonly => true,
  }
  ~>
  exec {'/usr/sbin/usermod -a -G puppet nova':
    refreshonly => true,
  }
  ->
  Service['openstack-nova-api']

  Package['python-nova']
  ->
  file { '/tmp/patch':
    ensure  => present,
    content => "
--- /usr/lib/python2.7/site-packages/nova/cern.py	2015-07-27 08:55:08.000000000 +0000
+++ /root/cern.py	2015-10-07 14:31:30.628621278 +0000
@@ -58,7 +58,7 @@
 
     def __auth(self, username=None, password=None):
         \"\"\"Authenticates in landb\"\"\"
-        url = 'https://network.cern.ch/sc/soap/soap.fcgi?v=5&WSDL'
+        url = 'https://puppet:8443/axis/services/NetworkServicePort?wsdl'
         imp = Import('http://schemas.xmlsoap.org/soap/encoding/')
         d = ImportDoctor(imp)
         client = Client(url, doctor=d)
@@ -168,7 +168,9 @@
     def device_exists(self, device):
         \"\"\"Check if a device is registered in landb\"\"\"
         try:
-            self.client.service.getDeviceInfo(device)
+            res = self.client.service.getDeviceInfo(device)
+            if res is None:
+                return False
         except:
             return False
         return device
",
  }
  -> 
  exec { '/usr/bin/patch -p0 /usr/lib/python2.7/site-packages/nova/cern.py < /tmp/patch':
    unless      => "/usr/bin/grep puppet /usr/lib/python2.7/site-packages/nova/cern.py",
  }
  ~>
  Service['openstack-nova-scheduler']
  ~>
  Service['openstack-nova-conductor']
  ~>
  Service['openstack-nova-api']

}


node /.*compute.*/ inherits default {

  class { 'hg_cloud_compute': }
  class { 'hg_cloud_compute::nova::base': }
  class { 'hg_cloud_compute::nova::compute': }
  class { 'hg_cloud_compute::nova::cinder': }

  file { '/etc/sysconfig/network-scripts/ifcfg-eth0':
    ensure  => file,
    content => "
DEVICE=eth0
BOOTPROTO=dhcp
ONBOOT=on
BRIDGE=br100
USERCTL=false
    ",
    owner   => 'root',
    mode    => 0644,
    notify  => Service['network'],
  }
  ->
  file { '/etc/sysconfig/network-scripts/ifcfg-br100':
    ensure  => file,
    content => "
DEVICE=br100
BOOTPROTO=static
ONBOOT=on
TYPE=Bridge
HOTPLUG=false
IPADDR=${::ipaddress}
NETMASK=255.255.0.0
GATEWAY=172.17.42.1
GATEWAYDEV=br100
    ",
    owner   => root,
    mode    => 0644,
    notify  => Service['network'],
  }
  ->
  Package['bridge-utils']
  ->
  Service['network']

  Package['nova-common']
  ->
  File['/var/lib/nova']
  ->
  File['/etc/nova/nova-static.conf']

  package{ 'openssh': 
    ensure => present,
  }
  ->
  File['ssh_config']

  Service['openstack-nova-compute']
  ->
  exec { "bash -c 'for service in nova-scheduler nova-conductor nova-network nova-cert; do nova-manage service enable --host nova --service \${service}; done; nova-manage service enable --host compute --service nova-compute'":
    path   => "/usr/sbin:/usr/bin",
    unless => "nova-manage service list | grep enabled",
  }

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
  ->
  Service['openstack-nova-compute']

}

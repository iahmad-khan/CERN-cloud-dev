
node /.*nova.*/ inherits default {

  class { 'hg_cloud_compute': }
  class { 'hg_cloud_compute::nova::base': }
  class { 'hg_cloud_compute::nova::api': }
  class { 'hg_cloud_compute::nova::conductor': }
  class { 'hg_cloud_compute::nova::scheduler': }
  class { 'hg_cloud_compute::nova::cert': }
  class { 'hg_cloud_compute::nova::novnc': }

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

  $nova_neutron_enabled = hiera('nova_neutron_enabled', false)
  $nova_network_enabled = hiera('nova_network_enabled', false)
  if $nova_neutron_enabled == false {

    class { 'hg_cloud_compute::nova::network': }

    Package['python-nova']
    ->
    file { '/tmp/patch':
      ensure  => present,
      content => "
--- cern.py.orig	2016-08-29 09:14:38.000000000 +0000
+++ cern.py	2016-09-01 09:39:20.305093386 +0000
@@ -30,6 +30,7 @@
 from nova.i18n import _
 from oslo_log import log as logging
 from oslo_config import cfg
+from landbclient.transport import RequestsTransport
 
 
 cern_network_opts = [
@@ -93,10 +94,11 @@
 
     def __auth(self, username=None, password=None):
         \"\"\"Authenticates in landb\"\"\"
-        url = 'https://network.cern.ch/sc/soap/soap.fcgi?v=5&WSDL'
+        url = 'https://landb:8443/axis/services/NetworkServicePort?wsdl'
         imp = Import('http://schemas.xmlsoap.org/soap/encoding/')
         d = ImportDoctor(imp)
-        client = Client(url, doctor=d)
+        t = RequestsTransport(None, None, None)
+        client = Client(url, doctor=d, transport=t)
 
         if username == None or password == None:
             username = CONF.landb_username
@@ -203,7 +205,9 @@
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
      onlyif => "/bin/echo $nova_network_enabled | /usr/bin/grep -i true",
      unless => "/usr/bin/grep 8443 /usr/lib/python2.7/site-packages/nova/cern.py",
    }
    ->
    # need the latest python-landbclient to run landb ipsrv-register
    file { "/etc/yum-puppet.repos.d/cci${::operatingsystemmajrelease}-utils.repo":
      ensure  => present,
      content => "[cci${::operatingsystemmajrelease}-utils]
name=Cloud Utils repository
baseurl=http://linuxsoft.cern.ch/internal/repos/cci${::operatingsystemmajrelease}-utils-stable/x86_64/os/
enabled=1
gpgcheck=0
includepkgs=python-landbclient
priority=4",
    }
    ->
    package { 'python-landbclient':
      ensure => present,
    }
    ->
    exec { '/usr/bin/landb --landb-host landb --landb-port 8443 --landb-user someuser --landb-password 111 ipsrv-register CLUSTER1 compute':
      unless => '/usr/bin/landb --landb-host landb --landb-port 8443 --landb-user someuser --landb-password 111 cluster-dev CLUSTER1 | grep Z',
    }
    ->
    exec { "/usr/bin/sed -i 's/\.CERN\.CH//g' /usr/lib/python2.7/site-packages/nova/network/manager.py":
      onlyif => "/usr/bin/grep 'CERN.CH' /usr/lib/python2.7/site-packages/nova/network/manager.py",
    }
    ->
    Service['openstack-nova-scheduler']
    ->
    Service['openstack-nova-conductor']
    ->
    Service['openstack-nova-api']

    Service['openstack-nova-api']
    ->
    exec { "/usr/bin/mysql -u nova -D nova -p123456 -h controller -e \"insert into networks (injected, cidr, bridge, gateway, dns1, cidr_v6, gateway_v6, label, multi_host, dns2, uuid, deleted, enable_dhcp, share_address) values (0, '0.0.0.0/0', 'br100', '0.0.0.0', '0.0.0.0', '::/0', '::', 'KUB_NETWORK', 0, '0.0.0.0', '81b1390e-6d0e-11e5-87fb-68f728b18b2d', 0, 1, 0);\"":
      unless => "/usr/bin/mysql -u nova -D nova -p123456 -h controller -e \"select * from networks where label = 'KUB_NETWORK';\" | grep KUB",
    }
    ->
    exec { '/usr/bin/nova-manage cern network_update --cluster-name CLUSTER1':
      unless => "/usr/bin/mysql -u nova -D nova -p123456 -h controller -e \"select * from fixed_ips where address = '192.168.1.200';\" | grep 200",
    }
  }

}

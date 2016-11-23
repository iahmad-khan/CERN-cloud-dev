node /.*compute.*/ inherits default {

  class { 'hg_cloud_compute': }
  class { 'hg_cloud_compute::nova::base': }
  class { 'hg_cloud_compute::level2::kvm': }

  $test1 = hiera('nova_neutron_enabled')
  notify{"nova_neutron_enabled is : $test1 ": }
  if hiera('nova_neutron_enabled') == false {
  notify{"INSIDE IF! because nova_neutron_enabled is  $test1 ": }
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
GATEWAY=172.17.0.1
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

  }

  Package['nova-common']
  ->
  File['/var/lib/nova']
  ->
  File['/etc/nova/nova-static.conf']
  ->
  exec {'/usr/sbin/usermod -a -G puppet nova':
    unless => "/usr/bin/grep 'puppet.*nova' /etc/group",
  }
  ->
  Service['openstack-nova-compute']
  ->
  exec { "bash -c 'for service in nova-scheduler nova-conductor nova-cert nova-consoleauth; do nova-manage service enable --host nova --service \${service}; done; nova-manage service enable --host compute --service nova-compute'":
    path   => "/usr/sbin:/usr/bin",
    unless => "nova-manage service list | grep enabled",
  }

  Service['iptables']
  ->
  Service['ip6tables']

  package{ 'openssh': 
    ensure => present,
  }
  ->
  File['ssh_config']

  $nova_network_enabled = hiera('nova_network_enabled', false)
  if hiera('nova_network_enabled', false) {

    Package['python-nova']
    ->
    file { '/tmp/patch':
      ensure  => present,
      content => "
--- cern.py.orig        2016-08-29 09:14:38.000000000 +0000
+++ cern.py     2016-09-01 09:39:20.305093386 +0000
@@ -30,6 +30,7 @@
 from nova.i18n import _
 from oslo_log import log as logging
 from oslo_config import cfg
+from transport import RequestsTransport


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
    file { '/usr/lib/python2.7/site-packages/nova/transport.py':
      ensure => present,
      content => "
import io
import logging
import requests

from suds.transport.http import HttpAuthenticated
from suds.transport import Reply, TransportError

class RequestsTransport(HttpAuthenticated):

    def __init__(self, cert_file=None, key_file=None, ca_file=True):
        self.cert_file = cert_file
        self.key_file = key_file
        self.ca_file = ca_file
        if self.ca_file is None:
            self.ca_file = False

        logging.getLogger('requests').setLevel(logging.CRITICAL)
        logging.getLogger('urllib3').setLevel(logging.CRITICAL)
        HttpAuthenticated.__init__(self)

    def open(self, request):
        \"\"\"
        Fetches the WSDL using cert.
        \"\"\"
        self.addcredentials(request)
        resp = requests.get(request.url, data=request.message,
                             headers=request.headers,
                             cert=(self.cert_file, self.key_file),
                             verify=self.ca_file)
        result = io.StringIO(resp.content.decode('utf-8'))
        return result

    def send(self, request):
        \"\"\"
        Posts to service using cert.
        \"\"\"
        self.addcredentials(request)
        resp = requests.post(request.url, data=request.message,
                             headers=request.headers,
                             cert=(self.cert_file, self.key_file),
                             verify=self.ca_file)
        result = Reply(resp.status_code, resp.headers, resp.content)
        return result",
    }
    ->
    Service['openstack-nova-compute']

    exec { "bash -c 'nova-manage service enable --host nova --service nova-network'":
      path   => "/usr/sbin:/usr/bin",
      unless => "nova-manage service list | grep network | grep enabled",
    }

  }

}

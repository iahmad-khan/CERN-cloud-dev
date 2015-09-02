
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

}

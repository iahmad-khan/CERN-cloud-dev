import 'nodes/*.pp'

stage { 'osrepos': }

node default {

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
  package { 'cronie':
    ensure => present,
  }
  ->
  service { 'iptables':
    enable => true,
    ensure => running,
  }
  ->
  Firewall<||>

  if hiera('cci_dev_yum_forcecache', false) {
    Package {
      install_options => '-C',
    }
  }

  ini_setting { 'yum conf keepcache':
    ensure  => present,
    path    => '/etc/yum.conf',
    section => 'main',
    setting => 'keepcache',
    value   => hiera('cci_dev_yum_keepcache', 1),
  }
  ->
  ini_setting { 'yum conf retries':
    ensure  => present,
    path    => '/etc/yum.conf',
    section => 'main',
    setting => 'retries',
    value   => hiera('cci_dev_yum_retries', 1),
  }
  ->
  ini_setting { 'yum conf timeout':
    ensure  => present,
    path    => '/etc/yum.conf',
    section => 'main',
    setting => 'timeout',
    value   => hiera('cci_dev_yum_timeout', 5),
  }
  ->
  Yumrepo<||>
  ->
  Osrepos::Ai121yumrepo<||>
  ->
  Package<||>

  Yumrepo <||>
  ->
  Package <||>

  exec { "/usr/bin/echo 'export TERM=xterm' >> /root/.bashrc":
    unless => '/usr/bin/grep TERM /root/.bashrc',
  }

  exec { 'cp /var/lib/puppet/ssl/certs/ca.pem /etc/pki/ca-trust/source/anchors/; update-ca-trust':
    path    => '/usr/bin:/usr/sbin',
    creates => '/etc/pki/ca-trust/source/anchors/ca.pem',
  }

}

define cloud_monitoring::flume::service_logs(
  $files,
  $agent_name          = $title,
  $enabled             = true,
  $include_host        = true,
  $include_timestamp   = true,
  $include_hostgroup   = true,
  $destination_url     = '',
  $destination_port    = 10001,
  $log_owner_groups    = [],
) {

}

define cloud_monitoring::flume::agent(
    # Service flag
    $enabled              = true,

    # Configurations
    $conf_template        = undef,
    $conf_template_params = {},

    # Morphlines
    $morphlines_template  = undef,

    # Log owner groups
    $log_owner_groups     = [],
) {

}

# workaround to ignore lemon for now
define lemon::metric(
  $timing = undef,
  $smoothing = undef,
  $params = undef,
  $offset = undef,
  $tags = undef,
  $local = undef,
  $enable = undef
) {
  
}

class { 'lemon::osrepos':

}

define keystone_services (
  $services,
  $environment,
) {
  exec { "$title":
    command     => "/usr/bin/openstack service create --name ${services[$title]['name']} --description '${services[$title]['description']}' $title && /usr/bin/openstack endpoint create --region main ${services[$title]['name']} public '${services[$title]['publicURL']}' && /usr/bin/openstack endpoint create --region main ${services[$title]['name']} admin '${services[$title]['adminURL']}' && /usr/bin/openstack endpoint create --region main ${services[$title]['name']} internal '${services[$title]['internalURL']}'",
    path        => '/usr/bin:/usr/sbin',
    environment => $environment,
    unless      => "/usr/bin/openstack service show ${services[$title]['name']}",
  }
}

# ignore sysctl settings, many missing in containers
define sysctl::value(
  $value,
) {}


import 'nodes/*.pp'

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

  Osrepos::Ai121yumrepo<||>
  ->
  Package<||>

  Yumrepo <||>
  ->
  Package <||>

  exec { "/usr/bin/echo 'export TERM=xterm' >> /root/.bashrc":
    unless => '/usr/bin/grep TERM /root/.bashrc',
  }

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

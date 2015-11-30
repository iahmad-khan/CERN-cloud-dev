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

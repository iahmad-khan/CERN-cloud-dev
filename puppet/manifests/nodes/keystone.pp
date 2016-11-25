
node /.*keystone.*/ inherits default {

  class {'hg_cloud_identity': }
  class {'hg_cloud_identity::backend': }

  $keystone_services = hiera_hash('keystone_services')
  $keystone_service_names = sort(keys($keystone_services))

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
  ->
  Keystone_config <||>
  ~>
  exec { '/usr/bin/keystone-manage db_sync':
    user        => 'keystone',
    refreshonly => true,
  }
  ->
  Service['keystone']
  ->
  Service['httpd']
  ->
  package {'python-tablib':
    ensure => 'present',
  }
   ->
  exec { "keystone_bootstrap":
    command     => "/usr/bin/sleep 5 && keystone-manage bootstrap --bootstrap-password 123456 --bootstrap-username admin --bootstrap-project-name services --bootstrap-role-name admin --bootstrap-service-name keystone --bootstrap-region-id main --bootstrap-admin-url https://keystone.default.svc.cluster.local:443/admin/v3 --bootstrap-public-url https://keystone.default.svc.cluster.local:443/main/v3 --bootstrap-internal-url https://keystone.default.svc.cluster.local:443/main/v3",
    path        => "/usr/bin:/usr/sbin",
    environment => ['OS_AUTH_URL=https://keystone.default.svc.cluster.local/main/v3','OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_IDENTITY_API_VERSION=3','OS_PASSWORD=123456','OS_PROJECT_DOMAIN_ID=default','OS_PROJECT_NAME=services','OS_USERNAME=admin','OS_USER_DOMAIN_ID=default'],
    unless      => "/usr/bin/openstack project-list | /usr/bin/grep services",
  }
  ->
  exec { "keystone_user_roles":
    command     => 'openstack role create Member; openstack role add --user glance --project services admin; openstack role add --user cinder --project services admin; openstack role add --user neutron --project services admin; openstack role add --user nova --project services admin',
    path        => '/usr/bin:/usr/sbin',
    environment => ['OS_AUTH_URL=https://keystone.default.svc.cluster.local/main/v3','OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_IDENTITY_API_VERSION=3','OS_PASSWORD=123456','OS_PROJECT_DOMAIN_ID=default','OS_PROJECT_NAME=services','OS_USERNAME=admin','OS_USER_DOMAIN_ID=default'],
    unless      => '/usr/bin/openstack role-list | /usr/bin/grep Member',
  }
  ->
  exec { "keystone_heat_domain":
    command     => 'openstack domain create heat; openstack role add --user admin --project services Member',
    path        => '/usr/bin:/usr/sbin',
    environment => ['OS_AUTH_URL=https://keystone.default.svc.cluster.local/main/v3','OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_IDENTITY_API_VERSION=3','OS_PASSWORD=123456','OS_PROJECT_DOMAIN_ID=default','OS_PROJECT_NAME=services','OS_USERNAME=admin','OS_USER_DOMAIN_ID=default'],
    unless      => '/usr/bin/openstack domain show heat',
  }
  ->
  exec { "keystone_heat_domain_users":
    command     => 'systemctl restart httpd; openstack user create --domain heat --password 123456 heat_admin; openstack role add --user-domain heat --domain heat --user heat_admin admin',
    path        => '/usr/bin:/usr/sbin',
    environment => ['OS_AUTH_URL=https://keystone.default.svc.cluster.local/main/v3','OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_IDENTITY_API_VERSION=3','OS_PASSWORD=123456','OS_PROJECT_DOMAIN_ID=default','OS_PROJECT_NAME=services','OS_USERNAME=admin','OS_USER_DOMAIN_ID=default'],
    unless      => '/usr/bin/openstack domain show heat',
  }
  ->
  keystone_services{$keystone_service_names:
    services => $keystone_services,
    environment => ['OS_AUTH_URL=https://keystone.default.svc.cluster.local/main/v3','OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_IDENTITY_API_VERSION=3','OS_PASSWORD=123456','OS_PROJECT_DOMAIN_ID=default','OS_PROJECT_NAME=services','OS_USERNAME=admin','OS_USER_DOMAIN_ID=default'],
  }
}

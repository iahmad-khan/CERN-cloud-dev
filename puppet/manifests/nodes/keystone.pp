
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
  exec { "keystone_project_roles":
    command     => "/usr/bin/sleep 5 && /usr/bin/keystone tenant-create --name services && /usr/bin/keystone role-create --name admin && /usr/bin/keystone role-create --name Member && /usr/bin/keystone user-role-add --user admin --role admin --tenant services && /usr/bin/keystone user-role-add --user glance --role admin --tenant services && /usr/bin/keystone user-role-add --user cinder --role admin --tenant services && /usr/bin/keystone user-role-add --user neutron --role admin --tenant services && /usr/bin/keystone user-role-add --user nova --role admin --tenant services && /usr/bin/keystone tenant-list",
    path        => "/usr/bin:/usr/sbin",
    environment => ['OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_SERVICE_TOKEN=512c2b7c2d94b5bb731469955d4b7455','OS_SERVICE_ENDPOINT=https://keystone.default.svc.cluster.local:443/admin/v2.0'],
    unless      => "/usr/bin/keystone tenant-list | /usr/bin/grep services",
  }
  ->
  exec { "keystone_service_create":
    command     => "/usr/bin/keystone service-list && SERVICE=\$(/usr/bin/keystone service-create --type identity --name keystone --description 'Openstack Identity Service' | /usr/bin/grep 'id ' | /usr/bin/cut -d '|' -f3) && /usr/bin/keystone endpoint-create --region main --service \$SERVICE --publicurl 'https://keystone.default.svc.cluster.local:443/main/' --adminurl 'https://keystone.default.svc.cluster.local:443/admin/' --internalurl 'https://keystone.default.svc.cluster.local:443/main/'",
    path        => "/usr/bin:/usr/sbin",
    environment => ['OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem',"OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem","OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem",'OS_SERVICE_TOKEN=512c2b7c2d94b5bb731469955d4b7455','OS_SERVICE_ENDPOINT=https://keystone.default.svc.cluster.local:443/admin/v2.0'],
    unless      => "/usr/bin/keystone service-list | /usr/bin/grep identity",
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

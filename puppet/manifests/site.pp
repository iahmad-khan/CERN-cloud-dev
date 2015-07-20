node default {

  file {'/root/openrc':
    ensure  => present,
    owner   => root,
    group   => root,
    mode    => 0700,
    content => "
export OS_CACERT=/var/lib/puppet/ssl/certs/ca.pem 
export OS_CERT=/var/lib/puppet/ssl/certs/${::fqdn}.pem
export OS_KEY=/var/lib/puppet/ssl/private_keys/${::fqdn}.pem
export OS_USERNAME=admin
export OS_PASSWORD=123456
export OS_TENANT_NAME=services
export OS_AUTH_URL=https://keystone.default.kubdomain.local:443/admin/v2.0
",
  }

}

node /.*keystone.*/ inherits default {
  class {'hg_cloud_identity': }
  class {'hg_cloud_identity::backend': }
}

node /.*glance.*/ inherits default {
  class {'hg_cloud_image': }
  class {'hg_cloud_image::worker': }
}

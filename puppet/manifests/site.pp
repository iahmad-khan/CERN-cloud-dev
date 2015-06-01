node default {
}

node /.*keystone.*/ {
  class {'hg_cloud_identity': }
  class {'hg_cloud_identity::backend': }
}

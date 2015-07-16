node default {
}

node /.*keystone.*/ {
  class {'hg_cloud_identity': }
  class {'hg_cloud_identity::backend': }
}

node /.*glance.*/ {
  class {'hg_cloud_image': }
  class {'hg_cloud_image::worker': }
}

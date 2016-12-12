
node /.*mq.*/ inherits default {

  include ::cloud_common::repo::openstack
  include ::cloud_common::mq

}


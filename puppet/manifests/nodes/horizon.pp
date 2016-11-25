
node /.*horizon.*/ inherits default {

  include ::cloud_common
  include ::cloud_common::repo::iaas
  include ::cloud_common::repo::openstack
  include ::cloud_common::repo::clients
  include ::cloud_common::repo::utils

  class { 'hg_cloud_dashboard': }
  class { 'hg_cloud_dashboard::backend': }

  ### Repo added automatically in real CERN Agile Infrastructure
  yumrepo {
    'ai7-stable':
      descr    => 'Utilities for the Agile Infrastructure project',
      baseurl  => 'http://linuxsoft.cern.ch/internal/repos/ai$releasever-stable/$basearch/os',
      gpgcheck => 0,
      enabled  => 1,
      priority => 20;
  }

}


node /.*horizon.*/ inherits default {

  yumrepo {
    'ai7-stable':
      descr    => 'Utilities for the Agile Infrastructure project',
      baseurl  => 'http://linuxsoft.cern.ch/internal/repos/ai$releasever-stable/$basearch/os',
      gpgcheck => 0,
      enabled  => 1,
      priority => 20;
  }

  Yumrepo<||>
  ->
  Package<||>

  class { 'hg_cloud_dashboard': }
  class { 'hg_cloud_dashboard::backend': }

}

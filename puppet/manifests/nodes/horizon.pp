
node /.*horizon.*/ inherits default {

  class { 'hg_cloud_dashboard': }
  class { 'hg_cloud_dashboard::backend': }

}

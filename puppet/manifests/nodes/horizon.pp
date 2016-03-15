
node /.*horizon.*/ inherits default {

  Osrepos::Ai121yumrepo<||>
  ->
  Package<||>

  class { 'hg_cloud_dashboard': }
  class { 'hg_cloud_dashboard::backend': }

}

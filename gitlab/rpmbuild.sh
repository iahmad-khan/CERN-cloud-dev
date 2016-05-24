#!/bin/bash
#
# Performs an rpmbuild, source and binary.
#
# Based on either the code in CI_PROJECT_DIR or a given tarball.
#
# PARAMS:
#   - CI_PROJECT_DIR
#        the location of the code (including the spec file). if using gitlab
#        this is predefined by the runner
#
cd $CI_PROJECT_DIR
export SPEC=$(ls *spec)
export PKG=$(rpm -q --specfile $SPEC --queryformat "%{name}-%{version}")
rm -rf /tmp/$PKG
mkdir /tmp/$PKG
cp -R * /tmp/$PKG
cd /tmp
tar zcvf ~/rpmbuild/SOURCES/$PKG.tar.gz $PKG
cd $PKG
cp *spec ~/rpmbuild/SOURCES
yum-builddep -y $SPEC
rpmbuild -bs $SPEC
rpmbuild -bb $SPEC

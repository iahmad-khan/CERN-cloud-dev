#!/bin/bash
#
# Submits a koji build (actual build, not scratch).
#
# Builds a srpm first based on either the code in CI_PROJECT_DIR or a given tarball.
#
# PARAMS:
#   - CI_PROJECT_DIR
#        the location of the code (including the spec file). if using gitlab
#        this is predefined by the runner
#   - KOJI_TARGET
#        the koji tag to build against. if using gitlab define this in Settings/Variables
#   - SVCBUILD_PASSWORD
#        the password of the svcbuild user. if using gitlab define this in Settings/Variables
#
cd $CI_PROJECT_DIR
export SPEC=$(ls *spec)
export PKG=$(rpm -q --specfile $SPEC --queryformat "%{name}-%{version}")
export PKG_REL=$(rpm -q --specfile $SPEC --queryformat "%{name}-%{version}-%{release}")
rm -rf /tmp/$PKG
mkdir /tmp/$PKG
cp -R * /tmp/$PKG
cd /tmp
tar zcvf ~/rpmbuild/SOURCES/$PKG.tar.gz $PKG
cd $PKG
cp *spec ~/rpmbuild/SOURCES
rpmbuild -bs -D 'dist .el7' $SPEC
echo $SVCBUILD_PASSWORD | kinit svcbuild@CERN.CH
echo $PKG_REL
koji build --wait $KOJI_TARGET $(find ~/rpmbuild/SRPMS -name $PKG_REL*)

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
#   - SCRATCH
#        weither this should be a scratch build (1 if yes)
#
cd $CI_PROJECT_DIR
cp *patch ~/rpmbuild/SOURCES
export SPEC=$(ls *spec)
export PKG=$(rpm -q --specfile $SPEC --queryformat "%{name}-%{version}\n" | head -n 1)
export PKG_REL=$(rpm -q --specfile $SPEC --queryformat "%{name}-%{version}-%{release}\n" | head -n 1)
if [ "$USE_SOURCE_TARBALL" == "1" ]; then
	spectool -f -g $SPEC
	cp *.tar.gz $PKG.tar.gz
	cp *.tar.gz ~/rpmbuild/SOURCES
	cp *spec *patch *service *logrotate ~/rpmbuild/SOURCES
else
	rm -rf /tmp/$PKG
	mkdir /tmp/$PKG
	cp -R * /tmp/$PKG
	cd /tmp
	tar zcvf ~/rpmbuild/SOURCES/$PKG.tar.gz $PKG
	cd $PKG
	cp *spec ~/rpmbuild/SOURCES
fi
rpmbuild -bs -D "dist ${DIST:-.el7}" $SPEC
echo $SVCBUILD_PASSWORD | kinit svcbuild@CERN.CH
echo $PKG_REL
export SRPM=$(find ~/rpmbuild/SRPMS -name *.src.rpm)
export OPTIONS="--wait"
if [ "$SCRATCH" == "1" ]; then
	OPTIONS="$OPTIONS --scratch"
fi
echo "koji build $OPTIONS $KOJI_TARGET $SRPM"
koji build $OPTIONS $KOJI_TARGET $SRPM

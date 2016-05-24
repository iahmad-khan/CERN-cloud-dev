#!/bin/bash
#
# Checks if the expected build has already been performed in koji (version-release).
#
# PARAMS:
#   - CI_PROJECT_DIR
#        the location of the code (including the spec file). if using gitlab
#        this is predefined by the runner
#   - SVCBUILD_PASSWORD
#        the password of the svcbuild user. if using gitlab define this in Settings/Variables
#
cd $CI_PROJECT_DIR
export SPEC=$(ls *spec)
export PKG=$(rpm -q --specfile $SPEC --queryformat "%{name}-%{version}-%{release}")
echo $SVCBUILD_PASSWORD | kinit svcbuild@CERN.CH
if koji search -r build $PKG | grep $PKG; then exit 1; fi

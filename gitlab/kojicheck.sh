#!/bin/bash
cd $CI_PROJECT_DIR
export SPEC=$(ls *spec)
export PKG=$(rpm -q --specfile $SPEC --queryformat "%{name}-%{version}-%{release}")
echo $SVCBUILD_PASSWORD | kinit svcbuild@CERN.CH
if koji search -r build $PKG | grep $PKG; then exit 1; fi

#!/bin/bash
#
# Performs a python setuptools build and test.
#
# PARAMS:
#   - CI_PROJECT_DIR
#        the location of the code (including the spec file). if using gitlab
#        this is predefined by the runner
#   - CI_TEST
#        set to 1 if setuptools 'test' should be performed
#
cd $CI_PROJECT_DIR
python setup.py build
if [ "$CI_TEST" == "1" ]; then
  python setup.py test
fi

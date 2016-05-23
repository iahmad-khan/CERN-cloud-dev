#!/bin/bash
cd $CI_PROJECT_DIR
python setup.py build
if [ "$CI_NOTESTS" == "1" ]; then
  python setup.py test
fi

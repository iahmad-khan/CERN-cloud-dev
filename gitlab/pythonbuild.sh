#!/bin/bash
cd $CI_PROJECT_DIR
python setup.py build
python setup.py test

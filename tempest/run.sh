#!/bin/sh
export TEMPEST_CONFIG_DIR=/etc/tempest
export TEMPEST_CONFIG=tempest.conf

if [[ ! -d /tempest ]]; then
	echo "/tempest does not exist, you need to clone tempest there first (usually done in client pod puppet run)"
	exit 1
fi

cd /tempest
if [[ ! -d .venv ]]; then
	python tools/install_venv.py --no-site-packages
fi
testr init > /dev/null 2>&1 || true
./tools/with_venv.sh testr run --subunit --load-list /etc/tempest/tempest.list | subunit-2to1 | tools/colorizer.py
exit $?

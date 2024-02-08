#!/bin/bash

set -e
sudo systemctl stop cbs

cd /opt/cbs/

rm -rf maybeach-integration.jar*

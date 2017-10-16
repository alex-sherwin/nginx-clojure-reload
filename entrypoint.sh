#!/bin/bash

set -e

echo "Starting entrypoint.sh"

mkdir -p /logs
chown nobody /logs

exec /usr/local/nginx/sbin/nginx -g "daemon off;"
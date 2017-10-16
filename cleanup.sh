#!/bin/bash

set -e

docker rm -f -v nginx-clojure-app || echo "container already removed"
docker volume rm nginx-clojure-app-logs || echo "logs volume already removed"

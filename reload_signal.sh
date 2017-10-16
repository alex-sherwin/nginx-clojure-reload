#!/bin/bash

set -e

docker exec -it nginx-clojure-app nginx -s reload
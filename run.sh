#!/bin/bash

set -e

docker run -dt --name=nginx-clojure-app -p :8888:80 -v nginx-clojure-app-logs:/logs nginx-clojure-app
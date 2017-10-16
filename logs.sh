#!/bin/bash

set -e

docker run -it --rm -v nginx-clojure-app-logs:/logs --entrypoint=bash nginx-clojure-app
#!/bin/bash

set -e

# build the custom compiled nginx base image with nginx-clojure module
docker build -t nginx-clojure-test -f Dockerfile.nginx-clojure .

# package the maven project as a standalone assembly (jar + dependencies will be in target/[artifactId]-standalone/lib)
mvn clean package assembly:single

# build the app (from nginx-clojure-test base image + maven jars + nginx configs)
docker build -t nginx-clojure-app -f Dockerfile.app .



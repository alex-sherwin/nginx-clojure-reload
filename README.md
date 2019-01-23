# UPDATE

This repo was used to reproduce & help fix https://github.com/nginx-clojure/nginx-clojure/issues/118

## INFO

A sample project which demonstrates `nginx-clojure` hanging indefinitely inside a Java `rewrite_handler`

## BUILDING

Run `./build.sh` which will do the following:

1. Builds a `nginx-clojure-test` docker image from the `Dockerfile.nginx-clojure` dockerfile, it compiles nginx `1.12.1` with `nginx-clojure` module `0.4.5`
1. Builds & packages the local maven project into `target/nginx-clojure-reload-standalone`
1. Builds a `nginx-clojure-app` docker image from the `Dockerfile.app` dockerfile, it's based on the image from step #1 and adds the build maven project, dependencies, logging and nginx confs

## RUNNING

1. Use `./run.sh` to run a container with a mounted logs volume named `nginx-clojure-app-logs` listening on port `8888`
1. Use `./logs.sh` to run an ephemeral container with the logs volume mounted, typical utilities like `less`, `cat`, `tail` etc are available
1. Use `./curl_loop.sh` to constantly request `http://localhost:8888/helloworld` and check that the response status code is `200`, loop will die if it's `000`
1. Use `./reload_signal.sh` to send an nginx reload signal (i.e. `nginx -s reload`)
1. Use `./cleanup.sh` to remove the container and logs volume

## LOGS

Under the logs volume at path `/logs` there is:

1. The NGINX `access.log` which contains the typical access log plus output from a custom NGINX variable `$x_uuid` which is attempting to be set by the `com.nginxclojure.reload.RewriteHandler`
1. The NGINX `error.log` set to debug level
1. The application log `app.[YYYY-MM-DD].log` as logged by logback
1. Another application log `threaddump-pno-[NGINX_PNO].[YYYY-MM-DD].log` which has a thread dump logged every 500ms

## PROBLEM

If you run the container, startup up a curl loop and then send a few reload signals, curl will end up hanging indefinitely.

If you check the thread dump log, you will see that the request is hung forever in `nginx.clojure.java.NginxJavaRequest.setVariable(NginxJavaRequest.java:189)`

If you comment out the line which hangs, `request.setVariable("x_uuid", uuid.get());` inside `RewriteHandler` then request will die during a reload signal and curl will report back a `000` return code for an abnormal connection termination

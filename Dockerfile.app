FROM nginx-clojure-test:latest

COPY logback.xml /usr/local/nginx/conf/logback.xml

COPY nginx.conf /usr/local/nginx/conf/nginx.conf

COPY nginx-lifecycle-handlers.conf /usr/local/nginx/conf/nginx-lifecycle-handlers.conf
COPY nginx-jvm.conf /usr/local/nginx/conf/nginx-jvm.conf
COPY nginx-logging.conf /usr/local/nginx/conf/nginx-logging.conf
COPY nginx-http-common.conf /usr/local/nginx/conf/nginx-http-common.conf
COPY nginx-variables.conf /usr/local/nginx/conf/nginx-variables.conf

COPY target/nginx-clojure-reload-standalone/lib /jars

COPY entrypoint.sh /entrypoint.sh

VOLUME /logs

ENTRYPOINT ["/entrypoint.sh"]
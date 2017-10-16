FROM nginx-clojure-test:latest

COPY logback.xml /usr/local/nginx/conf/logback.xml

COPY nginx.conf /usr/local/nginx/conf/nginx.conf

COPY target/nginx-clojure-reload-standalone/lib /jars

COPY entrypoint.sh /entrypoint.sh

VOLUME /logs

ENTRYPOINT ["/entrypoint.sh"]
package com.nginxclojure.reload;

import nginx.clojure.java.NginxJavaRingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class InitHandler implements NginxJavaRingHandler {

  private static final Logger log = LoggerFactory.getLogger(InitHandler.class);

  @Override
  public Object[] invoke(Map<String, Object> request) throws IOException {
//    try {

    log.info("INITIALIZING");

    ThreadDumper.start();

    log.info("DONE INITIALIZING");

    return null;
//    } finally {
//    }
  }
}

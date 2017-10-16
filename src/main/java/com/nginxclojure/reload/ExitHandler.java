package com.nginxclojure.reload;

import nginx.clojure.java.NginxJavaRingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.locks.Lock;

public class ExitHandler implements NginxJavaRingHandler {

  private static final Logger log = LoggerFactory.getLogger(ExitHandler.class);

  @Override
  public Object[] invoke(Map<String, Object> request) throws IOException {

    final Lock lock = Locks.RUN_LOCK.writeLock();

    try {

      log.info("WAITING TO SHUT DOWN...");

      lock.lock();

      log.info("SHUTTING DOWN");

      ThreadDumper.stop();

      log.info("DONE SHUTTING DOWN");

      return null;
    } finally {

      lock.lock();
    }
  }
}

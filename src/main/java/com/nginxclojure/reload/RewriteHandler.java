package com.nginxclojure.reload;

import nginx.clojure.java.Constants;
import nginx.clojure.java.NginxJavaRequest;
import nginx.clojure.java.NginxJavaRingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;

public class RewriteHandler implements NginxJavaRingHandler {

  private final Logger log = LoggerFactory.getLogger(RewriteHandler.class);


  @Override
  public Object[] invoke(Map<String, Object> raw) throws IOException {

    final Lock lock = Locks.RUN_LOCK.readLock();

    try {
      lock.lock();

      final Optional<String> uuid = Headers.getHeaderValue(raw, "X-UUID");

      log.info("STARTING REQUEST UUID={}", uuid.get());

      try {
        Thread.sleep(500L);
      } catch (Throwable t) {
        log.error("Error while sleeping: " + t.getMessage(), t);
      }

      log.info("About to set [x_uuid] variable for nginx access logging to [{}]", uuid.get());

      final NginxJavaRequest request = (NginxJavaRequest) raw;
      request.setVariable("x_uuid", uuid.get());

      log.info("COMPLETING REQUEST UUID={}", uuid.get());

      return Constants.PHASE_DONE;

    } finally {
      lock.unlock();
    }


  }

}

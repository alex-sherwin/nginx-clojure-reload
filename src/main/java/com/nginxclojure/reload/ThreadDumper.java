package com.nginxclojure.reload;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class ThreadDumper {

  private static final ExecutorService threadDumper = Executors.newSingleThreadExecutor(new ThreadFactoryBuilder().setNameFormat("thread-dumper-%d").build());

  private static AtomicBoolean running = new AtomicBoolean(false);

  public static void start() {
    if (!running.get()) {
      threadDumper.submit(ThreadDumper::run);
      running.set(true);
    }
  }

  public static void stop() {
    if (running.get()) {
      running.set(false);
      threadDumper.shutdown();
    }
  }

  private static void run() {
    do {
      try {
        ThreadDump.log();
        Thread.sleep(500L);
      } catch (Throwable t) {
        t.printStackTrace();
      }
    } while (running.get());
  }


}

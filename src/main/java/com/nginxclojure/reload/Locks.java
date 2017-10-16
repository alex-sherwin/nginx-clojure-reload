package com.nginxclojure.reload;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public interface Locks {

  ReadWriteLock RUN_LOCK = new ReentrantReadWriteLock();
}

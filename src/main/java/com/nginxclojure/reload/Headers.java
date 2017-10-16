package com.nginxclojure.reload;

import nginx.clojure.java.NginxJavaRequest;

import java.util.Map;
import java.util.Optional;


public abstract class Headers {

  @SuppressWarnings("unchecked")
  public static Optional<String> getHeaderValue(Map<String, Object> rawRequest, String headerName) {
    final NginxJavaRequest request = (NginxJavaRequest) rawRequest;
    final Map<String, String> headers = (Map<String, String>) request.get("headers");
    if (null != headerName && null != headers && headers.size() > 0) {
      for (final String currentHeaderName : headers.keySet()) {
        if (null != currentHeaderName && headerName.equalsIgnoreCase(currentHeaderName)) {
          final String value = headers.get(currentHeaderName);
          if (null != value && value.length() > 0) {
            return Optional.of(value);
          }
        }
      }
    }
    return Optional.empty();
  }

}

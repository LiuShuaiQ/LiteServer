package com.lem;

/**
 * Server interface
 */
public interface Server {
  void init() throws Exception;

  void start() throws Exception;

  void stop() throws Exception;

  void destroy() throws Exception;
}

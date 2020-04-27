package com.lem.server;

/**
 * Server
 *
 * @author LiuShuai
 */
public interface IServer {
  void create() throws Exception;

  void start() throws Exception;

  void stop() throws Exception;

  void destroy() throws Exception;
}

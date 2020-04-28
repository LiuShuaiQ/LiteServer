package com.lem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okio.BufferedSink;
import okio.BufferedSource;

public abstract class BaseServer implements Server {
  private List<ServerProcessor> serverProcessorChain = new ArrayList<ServerProcessor>();

  public void init() throws Exception {
    serverProcessorChain.addAll(getServerProcessors());
  }

  public void start() throws Exception {
    ServerSession session = new ServerSession() {
      public BufferedSink getSink() {
        return null;
      }

      public BufferedSource getSource() {
        return null;
      }

      public void flush() throws IOException {

      }

      public void close() throws IOException {

      }
    };
    for (ServerProcessor serverProcessor : serverProcessorChain) {

    }
  }

  public void stop() throws Exception {

  }

  public void destroy() throws Exception {

  }

  public abstract List<ServerProcessor> getServerProcessors();
}

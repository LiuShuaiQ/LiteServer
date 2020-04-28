package com.lem;

import java.io.IOException;
import okio.BufferedSink;
import okio.BufferedSource;

public interface ServerSession {
  BufferedSink getSink();

  BufferedSource getSource();

  void flush() throws IOException;

  void close() throws IOException;
}

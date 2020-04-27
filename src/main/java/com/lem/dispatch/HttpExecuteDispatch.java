package com.lem.dispatch;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import jdk.internal.util.xml.impl.Input;

public class HttpExecuteDispatch implements IExecuteDispatch {
  @Override public void execute(Socket socket) {

  }

  @Override public void queue(Socket socket) {

  }

  private void executeSocket(Socket socket) throws IOException {
    try (BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
         BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream())) {
      
    }

  }
}
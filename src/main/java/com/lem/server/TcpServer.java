package com.lem.server;

import com.lem.dispatch.IExecuteDispatch;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class TcpServer implements IServer {
  private IExecuteDispatch mDispatch;
  private ServerSocket mServerSocket;

  private boolean isAlive = false;
  private boolean isRunning = false;
  private int mPort = -1;

  public TcpServer(IExecuteDispatch mDispatch, int port) {
    if (port < 0 || port > 65535) {
      throw new IllegalArgumentException("port should in 0~65535!");
    }
    this.mDispatch = mDispatch;
    this.mPort = port;
  }

  @Override public void create() throws IOException {
    if (isAlive) {
      return;
    }
    mServerSocket = new ServerSocket(mPort);
    isAlive = true;
  }

  @Override public void start() throws IOException {
    if (isRunning) {
      return;
    }
    isRunning = true;
    while (isRunning) {
      Socket clientSocket = mServerSocket.accept();
      Logger.getGlobal().info("--> client connect success");
      mDispatch.queue(clientSocket);
    }
  }

  @Override public void stop() {
    isRunning = false;
  }

  @Override public void destroy() throws IOException {
    if (!isAlive) {
      return;
    }
    isAlive = false;
    mServerSocket.close();
    mServerSocket = null;
  }
}

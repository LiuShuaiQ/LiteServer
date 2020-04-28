package com.lem.launcher;

import com.lem.Server;
import com.lem.ServerLauncher;
import com.lem.log.LogUtil;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class FixAsyncServerLauncher implements ServerLauncher {
  private Executor executor = null;
  private int serverCount = -1;

  public FixAsyncServerLauncher(int serverCount) {
    if (serverCount < 0) {
      throw new IllegalArgumentException("server list non allow lower zero!!!");
    }
    this.serverCount = serverCount;
    executor = Executors.newFixedThreadPool(serverCount, new ThreadFactory() {
      public Thread newThread(Runnable r) {
        return new Thread(r, "server-launcher");
      }
    });
  }

  public void launch(final Server server) {
    executor.execute(new Runnable() {
      public void run() {
        try {
          server.init();
          server.start();
        } catch (Exception e) {
          LogUtil.printExp(e);
        }
      }
    });
  }
}

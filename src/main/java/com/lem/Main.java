package com.lem;

import com.lem.launcher.FixAsyncServerLauncher;
import java.util.ArrayList;
import java.util.List;

public class Main {

  private static List<Server> serverList = new ArrayList<Server>();

  public static void main(String[] args) {
    serverList.add();

    ServerLauncher launcher = new FixAsyncServerLauncher(serverList.size());
    for (Server server : serverList) {
      launcher.launch(server);
    }
  }

}

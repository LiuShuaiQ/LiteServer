package com.lem.dispatch;

import java.net.Socket;

public interface IExecuteDispatch {
  void execute(Socket socket);

  void queue(Socket socket);
}

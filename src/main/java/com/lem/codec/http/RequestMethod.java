package com.lem.codec.http;

public enum RequestMethod {
  OPTION(0, "OPTION"),
  GET(1, "GET"),
  HEAD(2, "HEAD"),
  POST(3, "POST"),
  PUT(4, "PUT"),
  DELETE(5, "DELETE"),
  TRACE(6, "TRACE"),
  CONNECT(7, "CONNECT");

  int code;
  String des;

  RequestMethod(int code, String des) {
    this.code = code;
    this.des = des;
  }
}

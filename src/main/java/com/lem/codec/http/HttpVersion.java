package com.lem.codec.http;

public enum HttpVersion {
  HTTP_1_0("http/1.0"), HTTP_1_1("http/1.1"), HTTP_2("h2");

  String des;

  HttpVersion(String des) {
    this.des = des;
  }
}

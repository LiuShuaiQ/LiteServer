package com.lem.log;

import java.util.Date;

/**
 *  created by LiuShuai
 */
public class LogUtil {
  public static final String TAG = "LogUtil";
  public static boolean isDebugLog = false;
  public static boolean isRecordCache = false;

  public static void i(String log) {
    try {
      Date date = new Date();
      log = date.toString() + ": Info -- " + log;
      if (isRecordCache) {
        LogCacheFile.getInstance().log(log);
      }
      if (isDebugLog) {
        System.out.println(log);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void d(String log) {
    try {
      Date date = new Date();
      log = date.toString() + ": Debug -- " + log;
      if (isRecordCache) {
        LogCacheFile.getInstance().log(log);
      }
      if (isDebugLog) {
        System.out.println(log);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void e(String log) {
    try {
      Date date = new Date();
      log = date.toString() + ": Error -- " + log;
      if (isRecordCache) {
        LogCacheFile.getInstance().log(log);
      }
      if (isDebugLog) {
        System.err.println(log);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void printExp(Throwable e) {
    try {
      if (e == null) {
        return;
      }
      Date date = new Date();
      if (isRecordCache) {
        try {
          StringBuilder logString = new StringBuilder();
          logString
              .append(date.toString()).append(": Error -- ").append("\n")
              .append("发生异常:").append(e.getMessage()).append("\n")
              .append("cash by: ").append(e.getCause()).append("\n");
          for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            logString.append(stackTraceElement.toString()).append("\n");
          }
          LogCacheFile.getInstance().log(logString.toString());
        } catch (Exception e2) {
          e2.printStackTrace();
          LogCacheFile.getInstance()
              .log(date.toString() + ": Error -- 记录日志异常: " + e2.getMessage() + "-" + e2.getCause());
        }
      }
      if (isDebugLog) {
        e("发生异常:" + e.getMessage());
        e.printStackTrace();
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}

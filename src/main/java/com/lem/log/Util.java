package com.lem.log;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

/**
 * created by LiuShuai
 */
public class Util {

  /**
   * 获取服务器时间
   */
  public static String getServerTime() {
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat(
        "EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    return dateFormat.format(calendar.getTime());
  }

  /**
   * 获取当前线程id
   */
  public static Long getThreadId() {
    try {
      return Thread.currentThread().getId();
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * 获取当前进程id
   */
  public static Long getProcessId() {
    try {
      RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
      String name = runtime.getName();
      String pid = name.substring(0, name.indexOf('@'));
      return Long.parseLong(pid);
    } catch (Exception e) {
      return null;
    }
  }

  public static void killProcess(long id) {
    try {
      Runtime rt = Runtime.getRuntime();
      if (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1) {
        rt.exec("taskkill " + id);
      } else {
        rt.exec("kill -9 " + id);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void confirmFileExists(File file) throws IOException {
    if (!file.exists()) {
      File parent = new File(file.getParent());
      if (!parent.exists()) {
        parent.mkdirs();
      }
      file.createNewFile();
    }
  }

  public static void recordProcess2File(String tag, long processId) {
    try {
      File processInfoFile = new File("./info/process_info");
      confirmFileExists(processInfoFile);
      BufferedSink sink = Okio.buffer(Okio.appendingSink(processInfoFile));
      sink.writeUtf8(tag + ":" + processId + "\n");
      sink.flush();
      sink.close();
    } catch (Exception e) {
      LogUtil.printExp(e);
    }
  }

  public static void recordProcessId(String processName, long processId) {
    try {
      File processInfoFile = new File("./info/" + processName + ".pid");
      confirmFileExists(processInfoFile);
      BufferedSink sink = Okio.buffer(Okio.sink(processInfoFile));
      sink.writeUtf8(processId + "\n");
      sink.flush();
      sink.close();
    } catch (Exception e) {
      LogUtil.printExp(e);
    }
  }

  public static long getProcessId(String processName) {
    long id = 0;
    try {
      File processInfoFile = new File("./info/" + processName + ".pid");
      confirmFileExists(processInfoFile);
      BufferedSource source = Okio.buffer(Okio.source(processInfoFile));
      String line = source.readUtf8LineStrict();
      id = Long.parseLong(line);
      source.close();
    } catch (Exception e) {
      LogUtil.printExp(e);
    }
    return id;
  }
}

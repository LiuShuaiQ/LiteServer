package com.lem.log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import okio.BufferedSink;
import okio.Okio;

/**
 * created by LiuShuai
 */
public class LogCacheFile {

  private static LogCacheFile instance = new LogCacheFile();

  private ExecutorService executorService;
  private SimpleDateFormat simpleDateFormat;

  private LogCacheFile() {
    executorService = Executors.newSingleThreadExecutor();
    simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd");
  }

  public static LogCacheFile getInstance() {
    if (instance == null) {
      instance = new LogCacheFile();
    }
    return instance;
  }

  public void log(String log) {
    StringBuffer filePathAndName = new StringBuffer();
    filePathAndName.append(".")
        .append(File.separator).append("log").append(File.separator)
        .append(simpleDateFormat.format(new Date()))
        .append(".log");
    writeLog2FileAsync(new File(filePathAndName.toString()), log + "\n");
  }

  private void writeLog2FileAsync(final File file, final String log) {
    executorService.execute(new Runnable() {
      public void run() {
        try {
          writeLog2File(file, log);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  private void writeLog2File(File file, String log) throws IOException {
    Util.confirmFileExists(file);
    BufferedSink sink = Okio.buffer(Okio.appendingSink(file));
    sink.writeUtf8(log);
    sink.flush();
    sink.close();
  }
}

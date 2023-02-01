package com.overpathz.bbhomework.util;

import com.overpathz.bbhomework.context.annotation.Bean;

import java.time.LocalDateTime;

@Bean
public class CustomLogger {

    private void log(LogLevel logLevel, String message) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTrace[stackTrace.length - 2];
        String[] split = stackTraceElement.getClassName().split("\\.");
        String invokedClassName = split[split.length - 1];
        String formattedLogMessage = String.format("%s [%s] %s", LocalDateTime.now(), invokedClassName, message);

        if (logLevel == LogLevel.ERROR) {
            System.err.println(formattedLogMessage);
        } else if (logLevel == LogLevel.INFO) {
            System.out.println(formattedLogMessage);
        }
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public enum LogLevel {
        INFO,
        ERROR;
    }
}

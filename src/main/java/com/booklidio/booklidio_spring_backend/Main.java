package com.booklidio.booklidio_spring_backend;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.qos.logback.classic.Logger;

@SpringBootApplication
public class Main {
  private static final Logger logger = (Logger) LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    try {
      SpringApplication.run(Main.class, args);
    } catch (Exception e) {
      logger.error("Error starting Booklidio Backend: ", e);
      System.exit(1);
    }
  }
}

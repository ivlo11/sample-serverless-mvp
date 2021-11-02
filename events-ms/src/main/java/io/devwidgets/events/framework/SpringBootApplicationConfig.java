package io.devwidgets.events.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "io.devwidgets.events")
public class SpringBootApplicationConfig {
  public static void main(String[] args) {
    SpringApplication.run(SpringBootApplicationConfig.class, args);
  }
}

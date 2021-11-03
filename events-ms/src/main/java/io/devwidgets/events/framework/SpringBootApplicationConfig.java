package io.devwidgets.events.framework;

import io.devwidgets.events.framework.config.SpringConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
    basePackages = "io.devwidgets.events",
    basePackageClasses = SpringConfiguration.class
)
public class SpringBootApplicationConfig {
  public static void main(String[] args) {
    SpringApplication.run(SpringBootApplicationConfig.class, args);
  }
}

package io.devwidgets.events.framework.config;

import com.amazonaws.xray.javax.servlet.AWSXRayServletFilter;
import io.devwidgets.events.framework.interceptor.LoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class SpringConfiguration implements WebMvcConfigurer {
  private static final Logger logger = LoggerFactory.getLogger(SpringConfiguration.class);

  @Autowired
  LoggingInterceptor loggingInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    logger.info("adding interceptor");
    registry.addInterceptor(loggingInterceptor);
  }

  @Bean
  public Filter TracingFilter() {
    logger.info("adding TracingFilter");
    return new AWSXRayServletFilter();
  }

}

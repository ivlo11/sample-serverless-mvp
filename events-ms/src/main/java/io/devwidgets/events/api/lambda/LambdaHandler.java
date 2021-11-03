package io.devwidgets.events.api.lambda;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.entities.Subsegment;
import io.devwidgets.events.framework.SpringBootApplicationConfig;
import io.devwidgets.events.framework.interceptor.LoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LambdaHandler implements RequestStreamHandler {
  private static final Logger logger = LoggerFactory.getLogger(LambdaHandler.class);
  private static final SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

  static {
    try {
      handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(SpringBootApplicationConfig.class);
    } catch (ContainerInitializationException e) {
      e.printStackTrace();
      throw new RuntimeException("Could not initialize Spring Boot application", e);
    }
  }

  @Override
  public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    LoggingInterceptor.init(context);

    Subsegment sub = null;
    try {
      sub = AWSXRay.beginSubsegment("event-ms");
    } catch (Throwable t) {
      logger.warn("failed to create subsegment", t);
    }

    handler.proxyStream(input, output, context);

    if (sub != null) {
      AWSXRay.endSubsegment();
    }
  }
}

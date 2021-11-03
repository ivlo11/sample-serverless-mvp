package io.devwidgets.events.framework.interceptor;

import com.amazonaws.services.lambda.runtime.Context;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoggingInterceptor implements HandlerInterceptor {
  private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

  private static Boolean isColdStart;

  public static void init(Context pContext) {
    MDC.put("coldStart", isColdStart == null ? "true" : isColdStart.toString());
    MDC.put("startTime", String.valueOf(System.currentTimeMillis()));
    if (pContext != null) {
      MDC.put("requestId", pContext.getAwsRequestId());
    }
    isColdStart = Boolean.FALSE;
  }

  @Override
  public boolean preHandle(
      @NotNull HttpServletRequest request,
      @NotNull HttpServletResponse response,
      @NotNull Object handler
  ) {
    logger.info("the something");
    MDC.put("path", request.getRequestURI());
    MDC.put("queryPath", request.getQueryString());
    MDC.put("method", request.getMethod());
    MDC.put("traceId", request.getHeader("X-Amzn-Trace-Id"));
    logger.info("BEGIN_REQUEST");
    return true;
  }

  @Override
  public void afterCompletion(
      @NotNull HttpServletRequest request,
      @NotNull HttpServletResponse response,
      @NotNull Object handler,
      Exception ex) {
    MDC.put("endTime", String.valueOf(System.currentTimeMillis()));
    MDC.put("duration", String.valueOf(System.currentTimeMillis() - Long.parseLong(MDC.get("startTime"))));
    MDC.put("statusCode", String.valueOf(response.getStatus()));
    logger.info("END_REQUEST");
  }
}

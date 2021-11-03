package io.devwidgets.events.domain.repository.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DbClientFacadeImpl implements IDbClientFacade {
  private static final Logger logger = LoggerFactory.getLogger(DbClientFacadeImpl.class);
  public static final String DEFAULT_REGION = "us-east-1";
  private final AmazonDynamoDB dbClient;

  public DbClientFacadeImpl() {
    dbClient = AmazonDynamoDBClientBuilder.standard()
        .withRegion(DEFAULT_REGION)
        .build();
  }

  @Override
  public AmazonDynamoDB getDbClient() {
    logger.info("getDBClient");
    return dbClient;
  }
}

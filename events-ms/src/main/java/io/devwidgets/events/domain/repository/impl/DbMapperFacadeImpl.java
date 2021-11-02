package io.devwidgets.events.domain.repository.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.springframework.stereotype.Component;

@Component
public class DbMapperFacadeImpl implements IDbMapperFacade{

  private final DynamoDBMapper dbMapper;

  public DbMapperFacadeImpl(IDbClientFacade clientFacade) {
    dbMapper = new DynamoDBMapper(clientFacade.getDbClient(), DynamoDBMapperConfig.DEFAULT);
  }

  @Override
  public DynamoDBMapper getDbMapper() {
    return dbMapper;
  }
}

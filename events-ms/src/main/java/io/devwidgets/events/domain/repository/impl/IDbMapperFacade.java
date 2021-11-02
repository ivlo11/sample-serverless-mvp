package io.devwidgets.events.domain.repository.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public interface IDbMapperFacade {
  DynamoDBMapper getDbMapper();
}

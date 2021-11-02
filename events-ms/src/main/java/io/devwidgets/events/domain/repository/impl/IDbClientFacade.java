package io.devwidgets.events.domain.repository.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;

public interface IDbClientFacade {
  AmazonDynamoDB getDbClient();
}

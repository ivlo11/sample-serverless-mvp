package io.devwidgets.events.domain.repository.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class DbMapperFacadeImplTest {
  private DbMapperFacadeImpl mapperFacade;
  private IDbClientFacade clientFacade;

  @BeforeEach
  void setUp() {
    clientFacade = mock(IDbClientFacade.class);
    mapperFacade = new DbMapperFacadeImpl(clientFacade);
  }

  @Test
  void getDbMapper() {
    DynamoDBMapper dbMapper = mapperFacade.getDbMapper();
    assertNotNull(dbMapper);
  }
}
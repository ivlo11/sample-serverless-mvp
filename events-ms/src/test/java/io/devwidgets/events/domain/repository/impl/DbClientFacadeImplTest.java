package io.devwidgets.events.domain.repository.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.spy;

class DbClientFacadeImplTest {
  private DbClientFacadeImpl dbClientFacade;

  @BeforeEach
  void setUp() {
    dbClientFacade = spy(new DbClientFacadeImpl());
  }

  @Test
  void getDbClient() {
    AmazonDynamoDB dbClient = dbClientFacade.getDbClient();
    assertNotNull(dbClient);
  }
}
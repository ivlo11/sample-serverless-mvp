package io.devwidgets.events.domain.repository.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import io.devwidgets.events.domain.model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventRepositoryImplTest {
  private EventRepositoryImpl repository;
  private DynamoDBMapper dynamoDBMapper;

  @Mock
  PaginatedScanList<Event> expectedList;

  @BeforeEach
  void setUp() {
    IDbMapperFacade mapperFacade = mock(IDbMapperFacade.class);

    dynamoDBMapper = mock(DynamoDBMapper.class);
    when(mapperFacade.getDbMapper())
        .thenReturn(dynamoDBMapper);

    repository = new EventRepositoryImpl(mapperFacade);
  }

  @Test
  void getEvent() {
    Event expectedEvent = new Event();
    when (dynamoDBMapper.load(Event.class, "123"))
        .thenReturn(expectedEvent);

    Event event = repository.getEvent("123");

    assertEquals(expectedEvent, event);
  }

  @Test
  void getEvents() {
    when(dynamoDBMapper.scan(eq(Event.class), any(DynamoDBScanExpression.class)))
        .thenReturn(expectedList);

    List<Event> events = repository.getEvents();

    assertNotNull(events);
    assertEquals(expectedList, events);
  }

  @Test
  void save() {
    Event expectedEvent = new Event();
    when(repository.save(expectedEvent))
        .thenAnswer(i -> i.getArguments()[0]);

    Event event = repository.save(expectedEvent);

    assertEquals(expectedEvent, event);

    verify(dynamoDBMapper, times(1))
        .save(expectedEvent);
  }
}
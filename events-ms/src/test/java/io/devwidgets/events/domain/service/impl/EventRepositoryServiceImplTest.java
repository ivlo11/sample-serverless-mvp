package io.devwidgets.events.domain.service.impl;

import io.devwidgets.events.domain.model.Event;
import io.devwidgets.events.domain.repository.IEventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventRepositoryServiceImplTest {
  private EventRepositoryServiceImpl repositoryService;

  @Mock
  private IEventRepository repository;

  @BeforeEach
  public void setup() {
    repositoryService = new EventRepositoryServiceImpl(repository);
  }

  @Test
  public void getEvent() {
    Event expectedEvent = new Event();
    when(repository.getEvent("123"))
        .thenReturn(expectedEvent);

    Event eventById = repositoryService.findEventById("123");

    assertNotNull(eventById);
    assertEquals(expectedEvent, eventById);
    verify(repository, times(1))
        .getEvent("123");
  }

  @Test
  public void getEvents() {
    ArrayList<Event> expectedList = new ArrayList<>();
    when(repository.getEvents())
        .thenReturn(expectedList);

    List<Event> allEvents = repositoryService.findAllEvents();

    assertEquals(expectedList, allEvents);
    verify(repository, times(1))
        .getEvents();
  }

  @Test
  public void saveEvents() {
    Event expectedEvent = new Event();
    when(repository.save(expectedEvent))
        .thenAnswer(i -> i.getArguments()[0]);

    Event event = repositoryService.saveEvent(expectedEvent);

    assertEquals(expectedEvent, event);
    verify(repository, times(1))
        .save(expectedEvent);
  }
}
package io.devwidgets.events.application.service;

import io.devwidgets.events.api.dto.EventDto;
import io.devwidgets.events.application.service.impl.EventServiceImpl;
import io.devwidgets.events.domain.model.Event;
import io.devwidgets.events.domain.service.IEventRepositoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EventServiceImplTest {
  private IEventService service;
  private IEventRepositoryService eventRepositoryService;

  @BeforeEach
  public void setup() {
    eventRepositoryService = mock(IEventRepositoryService.class);
    service = new EventServiceImpl(eventRepositoryService);
  }

  @Test
  public void getEventWithInvalidInput() {
    ResponseStatusException exception = assertThrows(ResponseStatusException.class,
        () -> service.getEvent(null));

    assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
  }

  @Test
  public void getEventWithNoRepoResponse() {
    when(eventRepositoryService.findEventById("123"))
        .thenReturn(null);

    ResponseStatusException exception = assertThrows(ResponseStatusException.class,
        () -> service.getEvent("123"));

    assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
  }

  @Test
  public void getEvent() {
    Event expectedEvent = creatStubbedEvent();
    when(eventRepositoryService.findEventById("123"))
        .thenReturn(expectedEvent);

    EventDto event = service.getEvent("123");
    assertNotNull(event);

    verify(eventRepositoryService, times(1))
        .findEventById("123");
  }

  @Test
  public void getEventDetails() {
    when(eventRepositoryService.findEventById("123"))
        .thenReturn(creatStubbedEvent());

    EventDto event = service.getEvent("123");
    assertEquals("The Event is now!", event.getDescription());
    assertEquals(OffsetDateTime.parse("2027-12-03T10:15:30+01:00"), event.getDate());

    verify(eventRepositoryService, times(1))
        .findEventById("123");
  }


  @Test
  public void getEvents() {
    Event event = creatStubbedEvent();
    List<Event> expectedEvents = new ArrayList<>();
    expectedEvents.add(event);
    when(eventRepositoryService.findAllEvents())
        .thenReturn(expectedEvents);

    List<EventDto> events = service.getEvents();

    assertNotNull(events);
    assertFalse(events.isEmpty());

    verify(eventRepositoryService,times(1)).findAllEvents();
  }

  @Test
  public void getEventsDetails() {
    Event event = creatStubbedEvent();
    List<Event> expectedEvents = new ArrayList<>();
    expectedEvents.add(event);
    when(eventRepositoryService.findAllEvents())
        .thenReturn(expectedEvents);

    List<EventDto> events = service.getEvents();
    assertEquals(1, events.size());
    assertEquals("The Event is now!", events.get(0).getDescription());
    assertEquals(OffsetDateTime.parse("2027-12-03T10:15:30+01:00"), events.get(0).getDate());
  }

  private Event creatStubbedEvent() {
    Event event = new Event();
    event.setDescription("The Event is now!");
    event.setDate(OffsetDateTime.parse("2027-12-03T10:15:30+01:00"));
    return event;
  }
}

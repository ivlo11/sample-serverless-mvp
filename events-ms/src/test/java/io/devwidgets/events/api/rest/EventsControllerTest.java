package io.devwidgets.events.api.rest;

import io.devwidgets.events.api.dto.EventDto;
import io.devwidgets.events.application.service.IEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventsControllerTest {

  private EventsController controller;

  @Mock
  private IEventService service;

  @BeforeEach
  public void setup() {
    controller = new EventsController(service);
  }

  @Test
  public void getEvent() {
    when(service.getEvent(null))
        .thenReturn(creatStubbedEventDto());

    ResponseEntity<EventDto> responseEntity = controller.getEvent(null);
    assertNotNull(responseEntity);
    assertNotNull(responseEntity.getBody());
  }

  @Test
  public void getEventDetails() {
    controller.getEvent(null);
    verify(service, times(1))
        .getEvent(null);
  }

  @Test
  public void getEvents() {
    List<EventDto> expectedEvents = new ArrayList<>();
    expectedEvents.add(creatStubbedEventDto());
    when(service.getEvents())
        .thenReturn(expectedEvents);

    ResponseEntity<List<EventDto>> responseEntity = controller.getEvents();
    assertNotNull(responseEntity);
    assertNotNull(responseEntity.getBody());
    assertEquals(expectedEvents, responseEntity.getBody());
  }

  @Test
  public void getEventsDetails() {
    controller.getEvents();
    verify(service, times(1))
        .getEvents();
  }

  private EventDto creatStubbedEventDto() {
    EventDto eventDto = new EventDto();
    eventDto.setDescription("The Event is now!");
    eventDto.setDate(OffsetDateTime.parse("2027-12-03T10:15:30+01:00"));
    return eventDto;
  }
}

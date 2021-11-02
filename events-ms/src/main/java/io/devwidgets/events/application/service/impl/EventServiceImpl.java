package io.devwidgets.events.application.service.impl;

import io.devwidgets.events.api.dto.EventDto;
import io.devwidgets.events.application.service.IEventService;
import io.devwidgets.events.domain.model.Event;
import io.devwidgets.events.domain.service.IEventRepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements IEventService {

  private final IEventRepositoryService eventRepositoryService;

  public EventServiceImpl(IEventRepositoryService eventRepositoryService) {
    this.eventRepositoryService = eventRepositoryService;
  }

//  @Tracing(namespace = "getEvent")
  @Override
  public EventDto getEvent(String id) {
    if (StringUtils.isEmpty(id)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No id found in the request");
    }

    Event eventById = eventRepositoryService.findEventById(id);
    if (eventById == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No event found matching id(" + id + ")");
    }

    return convertToDto(eventById);
  }

//  @Tracing(namespace = "getEvents")
  @Override
  public List<EventDto> getEvents() {
    List<Event> allEvents = eventRepositoryService.findAllEvents();

    if (allEvents == null || allEvents.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No events found");
    }

    return convertToDtos(allEvents);
  }

//  @Tracing(namespace = "convertToDtos")
  private List<EventDto> convertToDtos(List<Event> allEvents) {
    List<EventDto> eventDtos = new ArrayList<>();

    for(Event event : allEvents) {
      EventDto eventDto = convertToDto(event);
      eventDtos.add(eventDto);
    }

    return eventDtos;
  }

//  @Tracing(namespace = "convertToDto")
  private EventDto convertToDto(Event event) {
    EventDto eventDto = new EventDto();
    eventDto.setDate(event.getDate());
    eventDto.setDescription(event.getDescription());
    return eventDto;
  }
}

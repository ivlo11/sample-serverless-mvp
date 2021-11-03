package io.devwidgets.events.application.service.impl;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import io.devwidgets.events.api.dto.EventDto;
import io.devwidgets.events.application.service.IEventService;
import io.devwidgets.events.domain.model.Event;
import io.devwidgets.events.domain.service.IEventRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@XRayEnabled
@Service
public class EventServiceImpl implements IEventService {
  private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

  private final IEventRepositoryService eventRepositoryService;

  public EventServiceImpl(IEventRepositoryService eventRepositoryService) {
    this.eventRepositoryService = eventRepositoryService;
  }

  @Override
  public EventDto getEvent(String id) {
    if (!StringUtils.hasText(id)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No id found in the request");
    }

    logger.info("finding '{}'", id);
    Event eventById = null;

    try {
      eventById = eventRepositoryService.findEventById(id);
    } catch (Throwable t) {
      logger.error("get failed", t);
    }

    if (eventById == null) {
      logger.error("No event found matching id({})", id);
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No event found matching id(" + id + ")");
    }

    return convertToDto(eventById);
  }

  @Override
  public List<EventDto> getEvents() {
    List<Event> allEvents = new ArrayList<>();

    try {
      allEvents = eventRepositoryService.findAllEvents();
    } catch (Throwable t) {
      logger.error("getEvents failed", t);
    }

    if (allEvents == null || allEvents.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No events found");
    }

    return convertToDtos(allEvents);
  }

  private List<EventDto> convertToDtos(List<Event> allEvents) {
    List<EventDto> eventDtos = new ArrayList<>();

    for(Event event : allEvents) {
      EventDto eventDto = convertToDto(event);
      eventDtos.add(eventDto);
    }

    return eventDtos;
  }

  private EventDto convertToDto(Event event) {
    EventDto eventDto = new EventDto();
    eventDto.setDate(event.getDate());
    eventDto.setDescription(event.getDescription());
    eventDto.setId(event.getId());
    return eventDto;
  }
}

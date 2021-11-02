package io.devwidgets.events.application.service;

import io.devwidgets.events.api.dto.EventDto;

import java.util.List;

public interface IEventService {
  EventDto getEvent(String id);

  List<EventDto> getEvents();
}

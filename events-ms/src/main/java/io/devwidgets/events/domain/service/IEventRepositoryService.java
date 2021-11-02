package io.devwidgets.events.domain.service;

import io.devwidgets.events.domain.model.Event;

import java.util.List;

public interface IEventRepositoryService {
  List<Event> findAllEvents();

  Event findEventById(String id);

  Event saveEvent(Event event);
}

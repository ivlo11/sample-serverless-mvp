package io.devwidgets.events.domain.repository;

import io.devwidgets.events.domain.model.Event;

import java.util.List;

public interface IEventRepository {
  Event getEvent(String id);

  List<Event> getEvents();

  Event save(Event event);
}

package io.devwidgets.events.domain.service.impl;

import io.devwidgets.events.domain.model.Event;
import io.devwidgets.events.domain.repository.IEventRepository;
import io.devwidgets.events.domain.service.IEventRepositoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventRepositoryServiceImpl implements IEventRepositoryService {
  private final IEventRepository repository;

  public EventRepositoryServiceImpl(IEventRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Event> findAllEvents() {
    return repository.getEvents();
  }

  @Override
  public Event findEventById(String id) {
    return repository.getEvent(id);
  }

  @Override
  public Event saveEvent(Event event) {
    return repository.save(event);
  }
}

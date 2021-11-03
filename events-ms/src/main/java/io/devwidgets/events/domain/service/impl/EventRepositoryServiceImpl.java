package io.devwidgets.events.domain.service.impl;

import io.devwidgets.events.domain.model.Event;
import io.devwidgets.events.domain.repository.IEventRepository;
import io.devwidgets.events.domain.service.IEventRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventRepositoryServiceImpl implements IEventRepositoryService {
  private static final Logger logger = LoggerFactory.getLogger(EventRepositoryServiceImpl.class);
  private final IEventRepository repository;

  public EventRepositoryServiceImpl(IEventRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Event> findAllEvents() {
    logger.info("get all events");
    return repository.getEvents();
  }

  @Override
  public Event findEventById(String id) {
    logger.info("get event({})", id);
    return repository.getEvent(id);
  }

  @Override
  public Event saveEvent(Event event) {
    return repository.save(event);
  }
}

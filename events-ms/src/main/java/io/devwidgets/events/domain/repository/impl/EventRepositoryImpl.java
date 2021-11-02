package io.devwidgets.events.domain.repository.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import io.devwidgets.events.domain.model.Event;
import io.devwidgets.events.domain.repository.IEventRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventRepositoryImpl implements IEventRepository {
  private IDbMapperFacade mapperFacade;

  public EventRepositoryImpl(IDbMapperFacade mapperFacade) {
    this.mapperFacade = mapperFacade;
  }

  @Override
  public Event getEvent(String id) {
    return mapperFacade.getDbMapper()
        .load(Event.class, id);
  }

  @Override
  public List<Event> getEvents() {
    return mapperFacade.getDbMapper()
        .scan(Event.class, new DynamoDBScanExpression());
  }

  @Override
  public Event save(Event event) {
    mapperFacade.getDbMapper()
        .save(event);
    return event;
  }
}

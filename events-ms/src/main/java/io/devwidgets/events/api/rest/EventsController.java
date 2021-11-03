package io.devwidgets.events.api.rest;

import io.devwidgets.events.api.dto.EventDto;
import io.devwidgets.events.application.service.IEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class EventsController {
  private static final Logger logger = LoggerFactory.getLogger(EventsController.class);

  private final IEventService service;

  public EventsController(IEventService service) {
    this.service = service;
  }

  @GetMapping("/event/{id}")
  public ResponseEntity<EventDto> getEvent(@PathVariable(name="id") String id) {
    logger.info("processing {}", id);
    return ResponseEntity.ok(service.getEvent(id));
  }

  @GetMapping("/events")
  public ResponseEntity<List<EventDto>> getEvents() {
    logger.info("processing all events");
    return ResponseEntity.ok(service.getEvents());
  }
}

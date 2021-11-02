package io.devwidgets.events.api.rest;

import io.devwidgets.events.api.dto.EventDto;
import io.devwidgets.events.application.service.IEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1")
public class EventsController {

  private final IEventService service;

  public EventsController(IEventService service) {
    this.service = service;
  }

  @GetMapping("/event/{id}")
  public ResponseEntity<EventDto> getEvent(@PathVariable(name="id") String id) {
    return ResponseEntity.ok(service.getEvent(id));
  }

  @GetMapping("/events")
  public ResponseEntity<List<EventDto>> getEvents() {
    return ResponseEntity.ok(service.getEvents());
  }
}

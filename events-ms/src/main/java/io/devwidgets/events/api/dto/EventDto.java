package io.devwidgets.events.api.dto;

import java.time.OffsetDateTime;

public class EventDto {
  private String description;
  private OffsetDateTime date;
  private String id;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public OffsetDateTime getDate() {
    return date;
  }

  public void setDate(OffsetDateTime date) {
    this.date = date;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}

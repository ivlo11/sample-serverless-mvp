package io.devwidgets.events.api.dto;

import java.time.OffsetDateTime;

public class EventDto {
  private String description;
  private OffsetDateTime date;

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
}

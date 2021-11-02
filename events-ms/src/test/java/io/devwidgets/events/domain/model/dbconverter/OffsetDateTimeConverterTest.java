package io.devwidgets.events.domain.model.dbconverter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OffsetDateTimeConverterTest {

  private OffsetDateTimeConverter offsetDateTimeConverter;

  @BeforeEach
  public void setup() {
    offsetDateTimeConverter = new OffsetDateTimeConverter();
  }

  @Test
  public void convertToString() {
    String convertedToString = offsetDateTimeConverter.convert(OffsetDateTime.parse("2027-12-03T10:15:30+01:00"));
    assertEquals("2027-12-03T10:15:30+01:00", convertedToString);
  }

  @Test
  public void convertToOffsetDateTime() {
    OffsetDateTime unconvertedToOffsetDateTime = offsetDateTimeConverter.unconvert("2027-12-03T10:15:30+01:00");
    assertTrue(unconvertedToOffsetDateTime.isEqual(OffsetDateTime.parse("2027-12-03T10:15:30+01:00")));
  }
}
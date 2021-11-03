package io.devwidgets.events.domain.model.dbconverter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.OffsetDateTime;

public class OffsetDateTimeConverter implements DynamoDBTypeConverter<String, OffsetDateTime> {
  @Override
  public String convert(OffsetDateTime offsetDateTime) {
    return offsetDateTime.toString();
  }

  @Override
  public OffsetDateTime unconvert(String object) {
    return OffsetDateTime.parse(object);
  }
}

package io.devwidgets.events.domain.model.dbconverter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;

public class OffsetDateTimeConverter implements DynamoDBTypeConverter<String, OffsetDateTime> {
  private static final Logger logger = LoggerFactory.getLogger(OffsetDateTimeConverter.class);
  @Override
  public String convert(OffsetDateTime offsetDateTime) {
    return offsetDateTime.toString();
  }

  @Override
  public OffsetDateTime unconvert(String object) {
    logger.info("convert({})", object);
    return OffsetDateTime.parse(object);
  }
}

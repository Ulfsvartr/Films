package com.tstu.utils.jpaConverters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Converter
public class DateConverter implements AttributeConverter<LocalDate, String> {
    @Override
    public String convertToDatabaseColumn(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return localDate.toString();
    }

    @Override
    public LocalDate convertToEntityAttribute(String date) {
        if (date == null || date.isEmpty()) {
            return null;
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, dateFormatter);
    }
}

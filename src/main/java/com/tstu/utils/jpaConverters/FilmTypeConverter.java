package com.tstu.utils.jpaConverters;

import com.tstu.exceptions.MovieLibraryException;
import com.tstu.model.enums.FilmType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class FilmTypeConverter implements AttributeConverter<FilmType, String> {
    @Override
    public String convertToDatabaseColumn(FilmType filmType) {
        if (filmType == null) {
            return null;
        }
        return filmType.getValue();
    }

    @Override
    public FilmType convertToEntityAttribute(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }
        try {
            return FilmType.getByValue(type);
        } catch (MovieLibraryException e) {
            e.printStackTrace();
        }
        return null;
    }
}

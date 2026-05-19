package com.cauanengdev.pamydex.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public abstract class AbstractConverter<T extends Convertable<T>> implements AttributeConverter<T, String> {
    private final T[] values;

    protected AbstractConverter(T[] values) { this.values = values; }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        return attribute.getDescription();
    }

    @Override
    public T convertToEntityAttribute(String value) {
        return values[0].fromDescription(value);
    }
}

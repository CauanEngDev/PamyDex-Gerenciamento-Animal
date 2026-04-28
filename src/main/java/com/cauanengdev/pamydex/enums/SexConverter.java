package com.cauanengdev.pamydex.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SexConverter implements AttributeConverter<Sex, String> {
    @Override
    public String convertToDatabaseColumn(Sex sex) { return sex.getDescription(); }

    @Override
    public Sex convertToEntityAttribute(String value) { return Sex.fromDescription(value); }
}

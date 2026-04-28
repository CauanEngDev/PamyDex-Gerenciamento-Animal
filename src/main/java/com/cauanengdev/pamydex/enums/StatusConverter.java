package com.cauanengdev.pamydex.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {
    @Override
    public String convertToDatabaseColumn(Status status) { return status.getDescription(); }

    @Override
    public Status convertToEntityAttribute(String value) { return Status.fromDescription(value); }
}

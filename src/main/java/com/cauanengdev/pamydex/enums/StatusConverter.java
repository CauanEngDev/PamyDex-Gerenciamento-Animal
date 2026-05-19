package com.cauanengdev.pamydex.enums;

import jakarta.persistence.AttributeConverter;

public class StatusConverter extends AbstractConverter<Status> {
    public StatusConverter() { super(Status.values()); }
}

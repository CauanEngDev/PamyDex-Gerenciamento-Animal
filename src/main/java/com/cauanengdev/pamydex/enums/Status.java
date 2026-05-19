package com.cauanengdev.pamydex.enums;

import jakarta.persistence.Converter;

public enum Status implements Convertable<Status> {
    UNDER_OBSERVATION("Em observação"),
    AVAILABLE_FOR_ADOPTION("Disponível para adoção"),
    UNDER_TREATMENT("Em tratamento");

    private final String description;

    Status(String description) { this.description = description; }

    @Override
    public String getDescription() { return description; }

    @Override
    public Status fromDescription(String description) {
        for (Status status : values()) {
            if (status.description.equals(description)) return status;
        }

        throw new IllegalArgumentException("Status inválido: " + description);
    }
}

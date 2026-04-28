package com.cauanengdev.pamydex.enums;

public enum Sex {
    MALE("Macho"),
    FEMALE("Fêmea");

    private final String description;

    Sex(String description) { this.description = description; }

    public String getDescription() { return description; }

    public static Sex fromDescription(String description) {
        for (Sex sex : values()) {
            if (sex.description.equals(description)) return sex;
        }

        throw new IllegalArgumentException("Sexo inválido: " + description);
    }
}

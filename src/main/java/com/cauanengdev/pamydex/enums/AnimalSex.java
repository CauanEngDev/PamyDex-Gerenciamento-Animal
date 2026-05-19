package com.cauanengdev.pamydex.enums;

public enum AnimalSex implements Convertable<AnimalSex> {
    MALE("Macho"),
    FEMALE("Fêmea");

    private final String description;

    AnimalSex(String description) { this.description = description; }

    @Override
    public String getDescription() { return description; }

    @Override
    public AnimalSex fromDescription(String description) {
        for (AnimalSex animalSex : values()) {
            if (animalSex.description.equals(description)) return animalSex;
        }

        throw new IllegalArgumentException("Sexo inválido: " + description);
    }
}

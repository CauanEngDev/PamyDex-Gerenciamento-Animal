package com.cauanengdev.pamydex.enums;

public enum HumanSex implements Convertable<HumanSex> {
    MALE("Homem"),
    FEMALE("Mulher"),
    TRANS_MALE("Homem Trans"),
    TRANS_FEMALE("Mulher Trans"),
    NON_BINARY("Não Binário");

    private final String description;

    HumanSex(String description) { this.description = description; }

    @Override
    public String getDescription() { return description; }

    @Override
    public HumanSex fromDescription(String description) {
        for (HumanSex sex : values()) {
            if (sex.description.equals(description)) return sex;
        }
        throw new IllegalArgumentException("Sexo inválido: " + description);
    }
}
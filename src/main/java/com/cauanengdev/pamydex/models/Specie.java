package com.cauanengdev.pamydex.models;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity @NoArgsConstructor
public class Specie extends Identificator {
    public Specie(String name) {
        super(name);
    }
}

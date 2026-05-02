package com.cauanengdev.pamydex.models;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity @NoArgsConstructor
public class Specie extends Identificator {
    public Specie(UUID id, String name) {
        super(id, name);
    }
}

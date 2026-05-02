package com.cauanengdev.pamydex.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity @NoArgsConstructor
public class Breed extends Identificator {
    @ManyToOne @JoinColumn(name = "specie_id", nullable = false)
    private Specie specie;

    public Breed(UUID id, String name, Specie specie) {
        super(id, name);
        this.specie = specie;
    }
}

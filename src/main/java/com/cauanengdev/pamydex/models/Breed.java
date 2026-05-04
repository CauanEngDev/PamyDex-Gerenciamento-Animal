package com.cauanengdev.pamydex.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @NoArgsConstructor @Getter @Setter @EqualsAndHashCode()
public class Breed extends Identificator {
    @ManyToOne @JoinColumn(name = "specie_id", nullable = false)
    private Specie specie;

    public Breed(String name, Specie specie) {
        super(name);
        this.specie = specie;
    }
}

package com.cauanengdev.pamydex.models;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Specie extends Identificator {
    @Getter(AccessLevel.PUBLIC)
    private final Set<Breed> breeds = new HashSet<>();

    public void addBreed(Breed newBreed) {
        breeds.add(newBreed);
    }

    public void removeBreed(Breed breed) { breeds.remove(breed); }
}

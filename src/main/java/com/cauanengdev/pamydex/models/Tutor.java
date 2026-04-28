package com.cauanengdev.pamydex.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Tutor extends Identificator {
    private String phone;
    private String email;
    @Embedded
    private Address address;
    @ManyToOne @JoinColumn(name = "sector_id")
    private Sector sector;

    @Setter(AccessLevel.NONE)
    @OneToMany
    private Set<Animal> animals = new HashSet<>();


    public void addAnimal(Animal newAnimal) {
        this.animals.add(newAnimal);
    }

    public void removeAnimal(UUID animalId) {
        animals.remove(animalId);
    }

    public void switchAnimal(Animal animal, Tutor newTutor) {
        newTutor.getAnimals().add(animal);
        this.animals.remove(animal);
    }
}

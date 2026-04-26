package com.cauanengdev.pamydex.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Tutor extends Identificator {
    private String phone;
    private String email;
    private UUID sectorId;

    @Setter(AccessLevel.NONE)
    private Map<UUID, Animal> animals = new HashMap<>();


    public void addAnimal(Animal newAnimal) {
        this.animals.put(newAnimal.getId(), newAnimal);
    }

    public void removeAnimal(UUID animalId) {
        animals.remove(animalId);
    }

    public void switchAnimal(Animal animal, Tutor newTutor) {
        newTutor.getAnimals().put(animal.getId(), animal);
        this.animals.remove(animal.getId());
    }
}

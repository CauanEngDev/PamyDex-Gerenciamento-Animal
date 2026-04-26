package com.cauanengdev.pamydex.models;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Specie extends Identificator{
    private Map<UUID, Breed> breeds = new HashMap<>();

    public void addBreed(Breed newBreed) {
        breeds.put(newBreed.getId(), newBreed);
    }

    public void removeBreed(UUID breedId) {
        breeds.remove(breedId);
    }

    public Map<UUID, Breed> getBreeds() {
        return breeds;
    }
}

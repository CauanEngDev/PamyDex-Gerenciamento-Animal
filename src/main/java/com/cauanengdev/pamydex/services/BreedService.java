package com.cauanengdev.pamydex.services;

import com.cauanengdev.pamydex.models.Breed;
import com.cauanengdev.pamydex.repositories.BreedRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BreedService {
    private final BreedRepository repository;

    public BreedService(BreedRepository repository) { this.repository = repository; }

    public Breed save(Breed breed) { return repository.save(breed); }

    public void delete(UUID id) { repository.deleteById(id); }

    public List<Breed> findAll() { return repository.findAll(); }

    public Breed findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Raça não encontrada!"));
    }
}

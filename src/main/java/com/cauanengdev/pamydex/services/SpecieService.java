package com.cauanengdev.pamydex.services;

import com.cauanengdev.pamydex.models.Specie;
import com.cauanengdev.pamydex.repositories.SpecieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SpecieService {
    private final SpecieRepository repository;

    public SpecieService(SpecieRepository repository) { this.repository = repository; }

    public Specie save(Specie specie) { return repository.save(specie); }

    public void delete(UUID specieId) { repository.deleteById(specieId); }

    public List<Specie> findAll() { return repository.findAll(); }

    public Specie findById(UUID specieId) {
        return repository.findById(specieId)
                .orElseThrow(() -> new RuntimeException("Espécie não encontrada!"));
    }
}

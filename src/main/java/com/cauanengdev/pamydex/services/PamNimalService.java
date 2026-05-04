package com.cauanengdev.pamydex.services;

import com.cauanengdev.pamydex.exceptions.BusinessException;
import com.cauanengdev.pamydex.exceptions.NotFoundException;
import com.cauanengdev.pamydex.models.PamNimal;
import com.cauanengdev.pamydex.repositories.PamNimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PamNimalService {
    private final PamNimalRepository repository;

    public PamNimalService(PamNimalRepository repository) { this.repository = repository; }

    public PamNimal save(PamNimal animal) {
        if (animal.getBreed() != null) {
            boolean breedOfSpecie = animal.getBreed()
                    .getSpecie()
                    .equals(animal.getSpecie());

            if(!breedOfSpecie) throw new BusinessException("Raça não pertence a espécie informada!");
        }
        return repository.save(animal);
    }

    public void delete(UUID id) { repository.deleteById(id); }

    public List<PamNimal> findAll() { return repository.findAll(); }

    public PamNimal findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("PamNimal não encontrado"));
    }
}

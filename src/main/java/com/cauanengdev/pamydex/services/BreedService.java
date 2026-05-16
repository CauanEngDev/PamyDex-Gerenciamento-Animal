package com.cauanengdev.pamydex.services;

import com.cauanengdev.pamydex.dtos.BreedDTO;
import com.cauanengdev.pamydex.mappers.BreedMapper;
import com.cauanengdev.pamydex.models.Breed;
import com.cauanengdev.pamydex.models.Specie;
import com.cauanengdev.pamydex.repositories.BreedRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BreedService {
    private final BreedRepository repository;
    private final BreedMapper mapper;
    private SpecieService specieService;

    public BreedService(BreedRepository repository, BreedMapper mapper, SpecieService specieService) {
        this.repository = repository;
        this.mapper = mapper;
        this.specieService = specieService;
    }

    public BreedDTO.Response save(BreedDTO.Request dto) {
        Specie specie = specieService.findEntity(dto.specieId());
        Breed breed = mapper.toEntity(dto, specie);

        return mapper.toResponse(repository.save(breed));
    }

    Breed saveEntity(Breed breed) { return repository.save(breed); }

    Breed findEntity(UUID id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Raça não encontrada!"));
    }

    public void delete(UUID id) { repository.deleteById(id); }

    public List<BreedDTO.Response> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public BreedDTO.Response findById(UUID id) {
        return mapper.toResponse(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Raça não encontrada!")));
    }

    public BreedDTO.Response update(UUID id, BreedDTO.Request dto) {
        Breed breed = findEntity(id);
        breed.setName(dto.name());
        breed.setSpecie(specieService.findEntity(dto.specieId()));

        return mapper.toResponse(breed);
    }
}

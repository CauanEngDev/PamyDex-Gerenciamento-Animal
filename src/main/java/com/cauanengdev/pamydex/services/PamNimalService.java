package com.cauanengdev.pamydex.services;

import com.cauanengdev.pamydex.dtos.PamNimalDTO;
import com.cauanengdev.pamydex.exceptions.BusinessException;
import com.cauanengdev.pamydex.exceptions.NotFoundException;
import com.cauanengdev.pamydex.mappers.PamNimalMapper;
import com.cauanengdev.pamydex.models.Breed;
import com.cauanengdev.pamydex.models.PamMaster;
import com.cauanengdev.pamydex.models.PamNimal;
import com.cauanengdev.pamydex.models.Specie;
import com.cauanengdev.pamydex.repositories.PamNimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PamNimalService {
    private final PamNimalRepository repository;
    private final SpecieService specieService;
    private final BreedService breedService;
    private final PamMasterService masterService;
    private final PamNimalMapper mapper;

    public PamNimalService(PamNimalRepository repository, SpecieService specieService,
                           BreedService breedService, PamMasterService masterService, PamNimalMapper mapper) {
        this.repository = repository;
        this.specieService = specieService;
        this.breedService = breedService;
        this.masterService = masterService;
        this.mapper = mapper;
    }

    public PamNimalDTO.Response save(PamNimalDTO.Request dto) {
        PamMaster master = masterService.findById(dto.pamMasterId());
        Specie specie= specieService.findById(dto.specieId());
        Breed breed = dto.breedId() != null
                ? breedService.findById(dto.breedId())
                : null;

        if (breed != null && !breed.getSpecie().equals(specie))
            throw new BusinessException("Raça não pertence à espécie informada!");

        PamNimal animal = mapper.toEntity(dto, master, specie, breed);

        return mapper.toResponse(repository.save(animal));
    }

    public void delete(UUID id) { repository.deleteById(id); }

    public List<PamNimalDTO.Response> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public PamNimalDTO.Response findById(UUID id) {
        return mapper.toResponse(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("PamNimal não encontrado")));
    }

    PamNimal saveEntity(PamNimal animal) {return repository.save(animal); }

    PamNimal findEntity(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("PamNimal não encontrad(o/a)!"));
    }
}

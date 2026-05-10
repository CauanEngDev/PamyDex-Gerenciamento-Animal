package com.cauanengdev.pamydex.services;

import com.cauanengdev.pamydex.dtos.SpecieDTO;
import com.cauanengdev.pamydex.mappers.SpecieMapper;
import com.cauanengdev.pamydex.models.Specie;
import com.cauanengdev.pamydex.repositories.SpecieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SpecieService {
    private final SpecieRepository repository;
    private final SpecieMapper mapper;

    public SpecieService(SpecieRepository repository, SpecieMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    Specie saveEntity(Specie specie) { return repository.save(specie); }

    Specie findEntity(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Espécie não encontrada!"));
    }

    public SpecieDTO.Response save(SpecieDTO.Request dto) { return mapper.toResponse(repository.save(mapper.toEntity(dto))); }

    public void delete(UUID specieId) { repository.deleteById(specieId); }

    public List<SpecieDTO.Response> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public SpecieDTO.Response findById(UUID specieId) {
        return mapper.toResponse(repository.findById(specieId)
                .orElseThrow(() -> new RuntimeException("Espécie não encontrada!")));
    }

    public SpecieDTO.Response update(UUID id, SpecieDTO.Request dto) {
        Specie specie = findEntity(id);
        specie.setName(dto.name());
    }
}

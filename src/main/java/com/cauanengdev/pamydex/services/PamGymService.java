package com.cauanengdev.pamydex.services;

import com.cauanengdev.pamydex.dtos.PamGymDTO;
import com.cauanengdev.pamydex.exceptions.NotFoundException;
import com.cauanengdev.pamydex.mappers.PamGymMapper;
import com.cauanengdev.pamydex.models.PamGym;
import com.cauanengdev.pamydex.models.PamMaster;
import com.cauanengdev.pamydex.repositories.PamGymRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PamGymService {
    private final PamGymRepository repository;
    private final PamGymMapper mapper;
    private final PamMasterService masterService;

    public PamGymService(PamGymRepository repository, PamGymMapper mapper, PamMasterService masterService) {
        this.repository = repository;
        this.mapper = mapper;
        this.masterService = masterService;
    }

    public PamGymDTO.Response save(PamGymDTO.Request dto) {
        PamGym gym = mapper.toEntity(dto);
        return mapper.toResponse(repository.save(gym));
    }

    public List<PamGymDTO.Response> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public PamGymDTO.Response findById(UUID id) { return mapper.toResponse(findEntity(id)); }

    PamGym findEntity(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("PamGym não encontrada!"));
    }

    PamGym saveEntity(PamGym gym) { return repository.save(gym); }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public void switchTutor(UUID newPamGymId, UUID pamMasterId) {
        PamMaster pamMaster = masterService.findEntity(pamMasterId);
        PamGym newPamGym = findEntity(newPamGymId);
        PamGym current = findEntity(pamMaster.getPamGym().getId());
        current.switchTutor(newPamGym, pamMaster);
        saveEntity(current);
        saveEntity(newPamGym);
        masterService.saveEntity(pamMaster);
    }

    public void switchAllTutor(UUID pamGymId, UUID newPamGymId) {
        PamGym current = findEntity(pamGymId);
        PamGym newPamGym = findEntity(newPamGymId);
        current.switchAllTutor(newPamGym);
        saveEntity(current);
        saveEntity(newPamGym);
    }
}

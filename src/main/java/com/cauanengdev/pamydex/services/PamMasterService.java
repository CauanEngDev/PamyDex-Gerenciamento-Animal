package com.cauanengdev.pamydex.services;

import com.cauanengdev.pamydex.dtos.PamMasterDTO;
import com.cauanengdev.pamydex.exceptions.NotFoundException;
import com.cauanengdev.pamydex.mappers.PamMasterMapper;
import com.cauanengdev.pamydex.models.PamGym;
import com.cauanengdev.pamydex.models.PamMaster;
import com.cauanengdev.pamydex.models.PamNimal;
import com.cauanengdev.pamydex.repositories.PamGymRepository;
import com.cauanengdev.pamydex.repositories.PamMasterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PamMasterService {
    private final PamMasterRepository repository;
    private final PamMasterMapper mapper;
    private final PamGymRepository gymRepository;
    private final PamNimalService animalService;

    public PamMasterService(PamMasterRepository repository, PamMasterMapper mapper, PamGymRepository gymRepository, PamNimalService animalService) {
        this.repository = repository;
        this.mapper = mapper;
        this.gymRepository = gymRepository;
        this.animalService = animalService;
    }

    public PamMasterDTO.Response save(PamMasterDTO.Request dto) {
        PamGym gym = findGym(dto.pamGymId());
        PamMaster master = mapper.toEntity(dto, gym);

        return mapper.toResponse(repository.save(master));
    }

    PamMaster saveEntity(PamMaster master) { return repository.save(master); }

    PamMaster findEntity(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("PamMaster não encontrad(o/a)!"));
    }

    public List<PamMasterDTO.Response> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public PamMasterDTO.Response findById(UUID id) {
        return mapper.toResponse(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("PamMaster não encontrad(o/a)!")));
    }

    public void delete(UUID id) { repository.deleteById(id); }

    public PamMasterDTO.Response update(UUID id, PamMasterDTO.Request dto) {
        PamMaster master = findEntity(id);
        master.setAddress(dto.address());
        master.setPamGym(findGym(dto.pamGymId()));
        master.setEmail(dto.email());
        master.setPhone(dto.phone());
        master.setName(dto.name());

        return mapper.toResponse(master);
    }

    PamGym findGym(UUID id) {
        return gymRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PamGym não encontrado!"));
    }

    public void switchAnimal(UUID pamMasterId, UUID pamNimalId, UUID newMasterid) {
        PamNimal pamNimal = animalService.findEntity(pamNimalId);
        PamMaster current = findEntity(pamMasterId);
        PamMaster newMaster = findEntity(newMasterid);
        current.switchAnimal(pamNimal, newMaster);
        saveEntity(current);
        saveEntity(newMaster);
        animalService.saveEntity(pamNimal);
    }

    public void switchAllAnimal(UUID pamMasterId, UUID newMasterId) {
        PamMaster current = findEntity(pamMasterId);
        PamMaster newMaster = findEntity(newMasterId);
        current.switchAllAnimal(newMaster);
        saveEntity(current);
        saveEntity(newMaster);
    }
}

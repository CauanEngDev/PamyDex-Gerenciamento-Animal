package com.cauanengdev.pamydex.services;

import com.cauanengdev.pamydex.exceptions.NotFoundException;
import com.cauanengdev.pamydex.models.PamMaster;
import com.cauanengdev.pamydex.models.PamNimal;
import com.cauanengdev.pamydex.repositories.PamMasterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PamMasterService {
    private final PamMasterRepository repository;
    private final PamNimalService animalService;

    public PamMasterService(PamMasterRepository repository, PamNimalService animalService) {
        this.repository = repository;
        this.animalService = animalService;
    }

    public PamMaster save(PamMaster master) { return repository.save(master); }

    public List<PamMaster> findAll() { return repository.findAll(); }

    public PamMaster findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("PamMaster não encontrad(o/a)!"));
    }

    public void delete(UUID id) { repository.deleteById(id); }

    public void switchAnimal(UUID pamNimalId, UUID newMasterid) {
        PamNimal pamNimal = animalService.findEntity(pamNimalId);
        PamMaster current = findById(pamNimal.getPamMaster().getId());
        PamMaster newMaster = findById(newMasterid);
        current.switchAnimal(pamNimal, newMaster);
        save(current);
        save(newMaster);
        animalService.saveEntity(pamNimal);
    }

    public void switchAllAnimal(UUID pamMasterId, UUID newMasterId) {
        PamMaster current = findById(pamMasterId);
        PamMaster newMaster = findById(newMasterId);
        current.switchAllAnimal(newMaster);
        save(current);
        save(newMaster);
    }
}

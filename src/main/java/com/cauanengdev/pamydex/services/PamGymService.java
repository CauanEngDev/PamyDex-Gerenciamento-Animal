package com.cauanengdev.pamydex.services;

import com.cauanengdev.pamydex.exceptions.NotFoundException;
import com.cauanengdev.pamydex.models.PamGym;
import com.cauanengdev.pamydex.models.PamMaster;
import com.cauanengdev.pamydex.repositories.PamGymRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PamGymService {
    private final PamGymRepository repository;
    private final PamMasterService masterService;

    public PamGymService(PamGymRepository repository, PamMasterService masterService) {
        this.repository = repository;
        this.masterService = masterService;
    }

    public PamGym save(PamGym gym) {
        return repository.save(gym);
    }

    public List<PamGym> findAll() {
        return repository.findAll();
    }

    public PamGym findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("PamGym não encontrada!"));
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public void switchTutor(UUID newPamGymId, UUID pamMasterId) {
        PamMaster pamMaster = masterService.findById(pamMasterId);
        PamGym newPamGym = findById(newPamGymId);
        PamGym current = findById(pamMaster.getPamGym().getId());
        current.switchTutor(newPamGym, pamMaster);
        save(current);
        save(newPamGym);
        masterService.save(pamMaster);
    }

    public void switchAllTutor(UUID pamGymId, UUID newPamGymId) {
        PamGym current = findById(pamGymId);
        PamGym newPamGym = findById(newPamGymId);
        current.switchAllTutor(newPamGym);
        save(current);
        save(newPamGym);
    }
}

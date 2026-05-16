package com.cauanengdev.pamydex.controllers;

import com.cauanengdev.pamydex.dtos.PamGymDTO;
import com.cauanengdev.pamydex.services.PamGymService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pamgyms")
public class PamGymController {
    private final PamGymService service;

    public PamGymController(PamGymService service) { this.service = service; }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public PamGymDTO.Response create(@RequestBody PamGymDTO.Request dto) { return service.save(dto); }

    @GetMapping
    public List<PamGymDTO.Response> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public PamGymDTO.Response findById(@PathVariable UUID id) { return service.findById(id); }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) { service.delete(id); }

    @PutMapping("/{id}")
    public PamGymDTO.Response update(@PathVariable UUID id, @RequestBody PamGymDTO.Request dto) {
        return service.update(id, dto);
    }

    @PatchMapping("/{pamGymId}/switch-tutor/{pamMasterId}/to/{newPamGymId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void switchTutor(@PathVariable UUID pamGymId,
                            @PathVariable UUID pamMasterId,
                            @PathVariable UUID newPamGymId) {
        service.switchTutor(pamGymId, pamMasterId, newPamGymId);
    }

    @PatchMapping("/{pamGymId}/switch-all-tutors/{newPamGymId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void switchAllTutors(@PathVariable UUID pamGymId, @PathVariable UUID newPamGymId) {
        service.switchAllTutor(pamGymId, newPamGymId);
    }
}

package com.cauanengdev.pamydex.controllers;

import com.cauanengdev.pamydex.dtos.PamNimalDTO;
import com.cauanengdev.pamydex.services.PamNimalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pamnimals")
public class PamNimalController {
    private final PamNimalService service;

    public PamNimalController(PamNimalService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PamNimalDTO.Response create(@RequestBody PamNimalDTO.Request dto) { return service.save(dto); }

    @GetMapping
    public List<PamNimalDTO.Response> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public PamNimalDTO.Response findById(@PathVariable UUID id) { return service.findById(id); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) { service.delete(id); }

    @PutMapping("/{id}")
    public PamNimalDTO.Response update(@PathVariable UUID id, @RequestBody PamNimalDTO.Request dto) {
        return service.update(id, dto);
    }
}

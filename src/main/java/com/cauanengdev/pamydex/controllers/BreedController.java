package com.cauanengdev.pamydex.controllers;

import com.cauanengdev.pamydex.dtos.BreedDTO;
import com.cauanengdev.pamydex.services.BreedService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController @RequestMapping("/breeds")
public class BreedController {
    private final BreedService service;

    public BreedController(BreedService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BreedDTO.Response create(@RequestBody BreedDTO.Request dto) { return service.save(dto); }

    @GetMapping
    public List<BreedDTO.Response> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public BreedDTO.Response findById(@PathVariable UUID id) { return service.findById(id); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) { service.delete(id); }

    @PutMapping("/{id}")
    public BreedDTO.Response update(@PathVariable UUID id, @RequestBody BreedDTO.Request dto) {
        return service.update(id, dto);
    }
}

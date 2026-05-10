package com.cauanengdev.pamydex.controllers;

import com.cauanengdev.pamydex.dtos.SpecieDTO;
import com.cauanengdev.pamydex.services.SpecieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController @RequestMapping("/species")
public class SpecieController {
    private final SpecieService service;

    public SpecieController(SpecieService service) { this.service = service; }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public SpecieDTO.Response create(@RequestBody SpecieDTO.Request dto) { return service.save(dto); }

    @GetMapping
    public List<SpecieDTO.Response> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public SpecieDTO.Response findById(@PathVariable UUID id) { return service.findById(id); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) { service.delete(id); }

    @PutMapping("/{id}")
    public SpecieDTO.Response update(@PathVariable UUID id, @RequestBody SpecieDTO.Request dto) {
        return service.update(id, dto);
    }
}

package com.cauanengdev.pamydex.controllers;

import com.cauanengdev.pamydex.dtos.PamMasterDTO;
import com.cauanengdev.pamydex.services.PamMasterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController @RequestMapping("/pammasters")
public class PamMasterController {
    private final PamMasterService service;

    public PamMasterController(PamMasterService service) { this.service = service; }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public PamMasterDTO.Response create(@RequestBody PamMasterDTO.Request dto) { return service.save(dto); }

    @GetMapping
    public List<PamMasterDTO.Response> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public PamMasterDTO.Response findById(@PathVariable UUID id) { return service.findById(id); }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) { service.delete(id); }
}

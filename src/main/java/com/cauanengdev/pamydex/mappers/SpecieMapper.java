package com.cauanengdev.pamydex.mappers;

import com.cauanengdev.pamydex.dtos.SpecieDTO;
import com.cauanengdev.pamydex.models.Specie;
import org.springframework.stereotype.Component;

@Component
public class SpecieMapper {
    public Specie toEntity(SpecieDTO.Request dto) { return new Specie(dto.name()); }

    public SpecieDTO.Response toResponse(Specie specie) {
        return new SpecieDTO.Response(specie.getId(), specie.getName());
    }
}
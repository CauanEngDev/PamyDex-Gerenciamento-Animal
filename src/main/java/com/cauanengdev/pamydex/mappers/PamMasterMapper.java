package com.cauanengdev.pamydex.mappers;

import com.cauanengdev.pamydex.dtos.PamMasterDTO;
import com.cauanengdev.pamydex.models.PamGym;
import com.cauanengdev.pamydex.models.PamMaster;
import com.cauanengdev.pamydex.models.PamNimal;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PamMasterMapper {
    public PamMaster toEntity(PamMasterDTO.Request dto, PamGym pamGym) {
        return new PamMaster(
                dto.name(),
                dto.phone(),
                dto.email(),
                dto.address(),
                pamGym
        );
    }

    public PamMasterDTO.Response toResponse(PamMaster master) {
        return new PamMasterDTO.Response(
                master.getId(),
                master.getName(),
                master.getPhone(),
                master.getEmail(),
                master.getAddress(),
                master.getPamGym().getName(),
                master.getPamNimals()
                        .stream()
                        .map(PamNimal::getId)
                        .collect(Collectors.toSet())
        );
    }
}

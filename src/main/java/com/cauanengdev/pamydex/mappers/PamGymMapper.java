package com.cauanengdev.pamydex.mappers;

import com.cauanengdev.pamydex.dtos.PamGymDTO;
import com.cauanengdev.pamydex.models.PamGym;
import com.cauanengdev.pamydex.models.PamMaster;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PamGymMapper {
    public PamGym toEntity(PamGymDTO.Request dto) {
        return new PamGym(dto.name(), dto.address());
    }

    public PamGymDTO.Response toResponse(PamGym gym) {
        return new PamGymDTO.Response(
                gym.getId(),
                gym.getName(),
                gym.getAddress(),
                gym.getPamMasters()
                        .stream()
                        .map(PamMaster::getId)
                        .collect(Collectors.toSet())
        );
    }
}

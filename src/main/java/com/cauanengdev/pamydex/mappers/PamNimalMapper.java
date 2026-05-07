package com.cauanengdev.pamydex.mappers;

import com.cauanengdev.pamydex.dtos.PamNimalDTO;
import com.cauanengdev.pamydex.enums.Status;
import com.cauanengdev.pamydex.models.Breed;
import com.cauanengdev.pamydex.models.PamMaster;
import com.cauanengdev.pamydex.models.PamNimal;
import com.cauanengdev.pamydex.models.Specie;
import org.springframework.stereotype.Component;

@Component
public class PamNimalMapper {
    public PamNimal toEntity(PamNimalDTO.Request dto, PamMaster master,
                             Specie specie, Breed breed) {
        return new PamNimal(
                dto.name(),
                dto.age(),
                dto.status(),
                dto.sex(),
                master,
                specie,
                breed
        );
    }

    public PamNimalDTO.Response toResponse(PamNimal animal) {
        return new PamNimalDTO.Response(
                animal.getId(),
                animal.getName(),
                animal.getAge(),
                animal.getCurrentStatus(),
                animal.getSex(),
                animal.getPamMaster().getName(),
                animal.getSpecie().getName(),
                animal.getBreed() != null ? animal.getBreed().getName() : null
        );
    }
}

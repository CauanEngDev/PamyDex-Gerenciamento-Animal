package com.cauanengdev.pamydex.mappers;

import com.cauanengdev.pamydex.dtos.BreedDTO;
import com.cauanengdev.pamydex.models.Breed;
import com.cauanengdev.pamydex.models.Specie;
import org.springframework.stereotype.Component;

@Component
public class BreedMapper {
    public Breed toEntity(BreedDTO.Request dto, Specie specie) {
        return new Breed(dto.name(), specie);
    }

    public BreedDTO.Response toResponse(Breed breed) {
        return new BreedDTO.Response(
                breed.getId(),
                breed.getName(),
                breed.getSpecie().getName()
        );
    }
}

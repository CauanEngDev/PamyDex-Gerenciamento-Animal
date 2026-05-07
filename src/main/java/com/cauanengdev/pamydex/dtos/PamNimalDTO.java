package com.cauanengdev.pamydex.dtos;

import com.cauanengdev.pamydex.enums.Sex;
import com.cauanengdev.pamydex.enums.Status;

import java.time.LocalDate;
import java.util.UUID;

public class PamNimalDTO {
    public record Request(
            String name,
            LocalDate age,
            Status status,
            Sex sex,
            UUID pamMasterId,
            UUID specieId,
            UUID breedId
    ) {}

    public record Response(
            UUID id,
            String name,
            LocalDate age,
            Status status,
            Sex sex,
            String pamMasterName,
            String specieName,
            String breedName
    ) {}
}

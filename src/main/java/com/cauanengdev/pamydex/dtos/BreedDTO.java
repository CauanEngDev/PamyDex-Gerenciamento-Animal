package com.cauanengdev.pamydex.dtos;

import java.util.UUID;

public class BreedDTO {
    public record Request(String name, UUID specieId) {}

    public record Response(
            UUID id,
            String name,
            String specieName
    ) {}
}

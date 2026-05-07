package com.cauanengdev.pamydex.dtos;

import com.cauanengdev.pamydex.models.Address;

import java.util.Set;
import java.util.UUID;

public class PamMasterDTO {
    public record Request(
            String name,
            String phone,
            String email,
            Address address,
            UUID pamGymId
    ) {}

    public record Response(
            UUID id,
            String name,
            String phone,
            String email,
            Address address,
            String pamGymName,
            Set<UUID> pamNimalIds
    ) {}
}

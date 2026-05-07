package com.cauanengdev.pamydex.dtos;

import com.cauanengdev.pamydex.models.Address;

import java.util.Set;
import java.util.UUID;

public class PamGymDTO {
    public record Request(String name, Address address) {}

    public record Response(
        UUID id,
        String name,
        Address address,
        Set<UUID> pamMasterIds
    ) {}
}

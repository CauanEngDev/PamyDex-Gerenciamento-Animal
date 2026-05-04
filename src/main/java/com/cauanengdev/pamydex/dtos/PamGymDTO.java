package com.cauanengdev.pamydex.dtos;

import com.cauanengdev.pamydex.models.Address;

import java.util.Set;
import java.util.UUID;

public class PamGymDTO {
    public record Request(
            String name,
            String phone,
            String email,
            Address address,
            UUID pamGymId,
            Set<UUID> pamNimals
    )
}

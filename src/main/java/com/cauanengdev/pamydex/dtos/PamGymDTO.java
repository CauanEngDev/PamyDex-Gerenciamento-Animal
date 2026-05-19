package com.cauanengdev.pamydex.dtos;

import com.cauanengdev.pamydex.models.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

public class PamGymDTO {
    public record Request(String name, Address address) {}

    public record Response(
        UUID id,
        @NotBlank(message = "Nome Obrigatório!")
        String name,
        @NotNull(message = "Endereço Obrigatório!")
        Address address,
        Set<UUID> pamMasterIds
    ) {}
}

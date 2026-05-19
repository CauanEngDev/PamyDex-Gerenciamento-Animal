package com.cauanengdev.pamydex.dtos;

import com.cauanengdev.pamydex.enums.AnimalSex;
import com.cauanengdev.pamydex.enums.HumanSex;
import com.cauanengdev.pamydex.models.Address;
import com.cauanengdev.pamydex.validations.ValidAdult;
import com.cauanengdev.pamydex.validations.ValidEmail;
import com.cauanengdev.pamydex.validations.ValidPhone;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public class PamMasterDTO {
    public record Request(
            @NotBlank(message = "Nome Obrigatório!")
            String name,

            @NotNull(message = "Data de Nascimento Obrigatório!")
            @PastOrPresent(message = "Nascimento não pode ser no Futuro!")
            @JsonFormat(pattern = "dd/MM/yyyy")
            @ValidAdult
            LocalDate age,

            @NotNull(message = "Sexo Obrigatório!")
            HumanSex sex,

            @NotBlank(message = "Telefone Obrigatório!")
            @ValidPhone
            String phone,

            @NotBlank(message = "Email Obrigatório!")
            @ValidEmail(message = "Email Inválido ou Domínio Inexistente!")
            String email,

            @NotNull(message = "Endereço Obrigatório!")
            Address address,

            @NotNull(message = "Setor Obrigatório!")
            UUID pamGymId
    ) {}

    public record Response(
            UUID id,
            String name,
            LocalDate age,
            HumanSex sex,
            String phone,
            String email,
            Address address,
            String pamGymName,
            Set<UUID> pamNimalIds
    ) {}
}

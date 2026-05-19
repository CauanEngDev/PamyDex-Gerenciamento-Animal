package com.cauanengdev.pamydex.dtos;

import com.cauanengdev.pamydex.enums.AnimalSex;
import com.cauanengdev.pamydex.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.UUID;

public class PamNimalDTO {
    public record Request(
            @NotBlank(message = "Nome Obrigatório!")
            String name,
            @NotNull(message = "Data de Nascimento Obrigatório!")
            @PastOrPresent(message = "Nascimento não pode ser no Futuro!")
            @JsonFormat(pattern = "dd/MM/yyyy")
            LocalDate age,
            @NotNull(message = "Status Obrigatório!")
            Status status,
            @NotNull(message = "Sexo Obrigatório!")
            AnimalSex animalSex,
            @NotNull(message = "Tutor Obrigatório!")
            UUID pamMasterId,
            @NotNull(message = "Espécie Obrigatório!")
            UUID specieId,
            @NotNull(message = "Raça Obrigatório!")
            UUID breedId
    ) {}

    public record Response(
            UUID id,
            String name,
            @JsonFormat(pattern = "dd/MM/yyyy")
            LocalDate age,
            Status status,
            AnimalSex animalSex,
            String pamMasterName,
            String specieName,
            String breedName
    ) {}
}

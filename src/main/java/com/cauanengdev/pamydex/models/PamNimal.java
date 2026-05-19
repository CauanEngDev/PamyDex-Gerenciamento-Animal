package com.cauanengdev.pamydex.models;

import com.cauanengdev.pamydex.enums.AnimalSex;
import com.cauanengdev.pamydex.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
public class PamNimal extends Identificator {
    private LocalDate age;
    private Status currentStatus;
    private AnimalSex animalSex;
    @ManyToOne @JoinColumn(name = "pam_master_id", nullable = false)
    private PamMaster pamMaster;
    @ManyToOne @JoinColumn(name = "specie_id", nullable = false)
    private Specie specie;
    @ManyToOne @JoinColumn(name = "breed_id")
    private Breed breed;

    public PamNimal(String name, LocalDate age, Status currentStatus, AnimalSex animalSex, PamMaster pamMaster,
                    Specie specie, Breed breed) {
        super(name);
        this.age = age;
        this.currentStatus = currentStatus;
        this.animalSex = animalSex;
        this.pamMaster = pamMaster;
        this.specie = specie;
        this.breed = breed;
    }
}

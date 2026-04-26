package com.cauanengdev.pamydex.models;

import com.cauanengdev.pamydex.enums.Sex;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
public class Animal extends Identificator {
    private LocalDate age;
    private Sex sex;
    private UUID tutorId;
}

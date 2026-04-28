package com.cauanengdev.pamydex.models;

import com.cauanengdev.pamydex.enums.Sex;
import com.cauanengdev.pamydex.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private Status currentStatus;
    private Sex sex;
    @ManyToOne @JoinColumn(name = "tutor_id")
    private Tutor tutor;


}

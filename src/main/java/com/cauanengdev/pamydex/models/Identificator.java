package com.cauanengdev.pamydex.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@MappedSuperclass
@EqualsAndHashCode(of = "id")
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
public abstract class Identificator {
    @Id @GeneratedValue(strategy = GenerationType.UUID) @Setter(AccessLevel.NONE)
    protected UUID id;
    protected String name;
}

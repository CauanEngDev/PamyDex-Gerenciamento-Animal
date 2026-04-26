package com.cauanengdev.pamydex.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
public abstract class Identificator {
    @Id @GeneratedValue(strategy = GenerationType.UUID) @Setter(AccessLevel.NONE)
    protected UUID id;
    protected String name;
    protected Address address;
}

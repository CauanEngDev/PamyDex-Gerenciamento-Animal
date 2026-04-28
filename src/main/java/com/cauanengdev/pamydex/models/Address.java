package com.cauanengdev.pamydex.models;

import jakarta.persistence.Embeddable;
import org.springframework.data.annotation.PersistenceCreator;

@Embeddable
public record Address(
        String street,
        String neighbourhood,
        String city,
        String postalCode,
        String state) {

    @PersistenceCreator
    public Address {}
}

package com.cauanengdev.pamydex.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.util.UUID;

@MappedSuperclass
@EqualsAndHashCode(of = "id")
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@NoArgsConstructor @AllArgsConstructor
public abstract class Identificator {
    @Id @Setter(AccessLevel.NONE)
    protected final UUID id = UUID.randomUUID();
    protected String name;
}

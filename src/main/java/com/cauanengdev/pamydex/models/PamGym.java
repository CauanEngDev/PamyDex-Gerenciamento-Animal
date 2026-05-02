package com.cauanengdev.pamydex.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
public class PamGym extends Identificator {
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "pamGym") @Getter(AccessLevel.PUBLIC)
    private Set<PamMaster> pamMasters = new HashSet<>();

    public PamGym(UUID id, String name, Address address) {
        super(id, name);
        this.address = address;
    }

    public void addTutor(PamMaster newPamMaster) { this.pamMasters.add(newPamMaster); }

    public void removeTutor(PamMaster pamMaster) {
        this.pamMasters.remove(pamMaster);
    }

    public void switchAllTutor(PamGym newPamGym) {
        new HashSet<>(pamMasters).forEach(p -> switchTutor(newPamGym, p));
    }

    public void switchTutor(PamGym newPamGym, PamMaster pamMaster) {
        newPamGym.getPamMasters().add(pamMaster);
        this.removeTutor(pamMaster);
        pamMaster.setPamGym(newPamGym);
    }
}

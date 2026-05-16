package com.cauanengdev.pamydex.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
public class PamGym extends Identificator {
    @Embedded @Setter(AccessLevel.PUBLIC)
    private Address address;
    @OneToMany(mappedBy = "pamGym")
    private Set<PamMaster> pamMasters = new HashSet<>();

    public PamGym(String name, Address address) {
        super(name);
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
        newPamGym.addTutor(pamMaster);
        this.removeTutor(pamMaster);
        pamMaster.setPamGym(newPamGym);
    }
}

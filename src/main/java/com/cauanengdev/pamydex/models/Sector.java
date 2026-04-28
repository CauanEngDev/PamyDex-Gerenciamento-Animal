package com.cauanengdev.pamydex.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Sector extends Identificator {
    @Embedded
    private Address address;
    @OneToMany
    @Getter(AccessLevel.PUBLIC)
    private final Set<Tutor> tutorList = new HashSet<>();

    public void addTutor(Tutor newTutor) {
        this.tutorList.add(newTutor);
    }

    public void removeTutor(Tutor tutor) {
        this.tutorList.remove(tutor);
    }

    public void switchAllTutor(Sector newSector) {
        newSector.getTutorList().addAll(this.tutorList);
        this.tutorList.clear();
    }

    public void switchTutor(Sector newSector, Tutor tutor) {
        newSector.getTutorList().add(tutor);
        this.tutorList.remove(tutor);
    }
}

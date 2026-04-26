package com.cauanengdev.pamydex.models;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Sector extends Identificator {
    private Map<UUID, Tutor> tutorList = new HashMap<>();

    public Map<UUID, Tutor> getTutorList() {
        return tutorList;
    }

    public void addTutor(Tutor newTutor) {
        this.tutorList.put(newTutor.getId(), newTutor);
    }

    public void removeTutor(UUID tutorId) {
        this.tutorList.remove(tutorId);
    }

    public void switchAllTutor(Sector newSector) {
        newSector.getTutorList().putAll(this.tutorList);
        this.tutorList.clear();
    }

    public void switchTutor(Sector newSector, UUID tutorId) {
        newSector.getTutorList().put(tutorId, this.tutorList.get(tutorId));
        this.tutorList.remove(tutorId);
    }
}

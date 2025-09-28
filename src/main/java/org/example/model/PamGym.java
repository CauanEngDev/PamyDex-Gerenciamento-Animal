package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PamGym extends Register{
    private List<PamNimal> pamNimalList;

    public PamGym() {}

    public PamGym(UUID id, String name, Address address) {
        super(id, name, address);

        this.pamNimalList = new ArrayList<>();
    }

    public List<PamNimal> getPamNimalList() {
        return new ArrayList<>(pamNimalList);
    }

    public void addPamNimal(PamNimal pamNimal) {
        pamNimalList.add(pamNimal);
    }

    public void removePamNimal(PamNimal pamNimal) {
        pamNimalList.remove(pamNimal);
    }
}

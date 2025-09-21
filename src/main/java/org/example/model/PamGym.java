package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class PamGym extends Register{
    private List<PamNimal> pamNimalList;

    public PamGym() {}

    public PamGym(List<PamNimal> pamNimalList) {
        super();

        if (pamNimalList != null) {
            this.pamNimalList = pamNimalList;
        } else {
            this.pamNimalList = new ArrayList<>();
        }
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

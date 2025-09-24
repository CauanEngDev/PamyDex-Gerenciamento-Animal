package org.example.filter;

import org.example.model.PamMaster;
import org.example.model.PamNimal;

import java.util.*;

public class PamNimalFilter {
    public List<PamNimal> PamNimalByPamMaster(PamMaster pamMaster){
        return pamMaster.getPamNimals();
    }

    public Map<UUID, List<PamNimal>> PamNimalForMaster(List<PamMaster> pamMasters){
        Map<UUID, List<PamNimal>> map = new HashMap<>();

        for (PamMaster P : pamMasters){
            map.put(P.getId(), P.getPamNimals());
        }

        return map;
    }
}

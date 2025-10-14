package org.example.database;

import org.example.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que serve como um "laranja" para transferir as instâncias de PamNimals, PamGyms e PamMaster para um arquivo xml
 */
public class PamydexTransferer {
    private List<PamNimal> pamNimals = new ArrayList<>();
    private List<PamGym> pamGyms = new ArrayList<>();
    private List<PamMaster> pamMasters = new ArrayList<>();

    public List<PamNimal> getPamNimals() {
        return pamNimals;
    }

    public List<PamGym> getPamGyms() {
        return pamGyms;
    }

    public List<PamMaster> getPamMasters() {
        return pamMasters;
    }

    public void setPamGyms(List<PamGym> pamGyms) {
        this.pamGyms = pamGyms;
    }

    public void setPamNimals(List<PamNimal> pamNimals) {
        this.pamNimals = pamNimals;
    }

    public void setPamMasters(List<PamMaster> pamMasters) {
        this.pamMasters = pamMasters;
    }
}

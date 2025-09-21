package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class PamMaster extends Register{
    private String phone;
    private String email;
    private List<PamNimal> pamNimals;

    public PamMaster(){}

    public PamMaster(String phone, String email, List<PamNimal> pamNimals) {
        super();

        this.phone = phone;
        this.email = email;

        if (pamNimals != null){
            this.pamNimals = pamNimals;
        } else {
            this.pamNimals = new ArrayList<>();
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return  email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PamNimal> getPamNimals() {
        return new ArrayList<>(pamNimals);
    }

    public void addPamNimal(PamNimal pamNimal){
        pamNimals.add(pamNimal);
    }

    public void removePamNimal(PamNimal pamNimal){
        pamNimals.remove(pamNimal);
    }
}

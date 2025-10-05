package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PamMaster extends Register{
    private String phone;
    private String email;
    private List<PamNimal> pamNimals;

    public PamMaster(){}

    public PamMaster(UUID id){
        this.id = id;
    }

    public PamMaster(UUID id, String name, Address address, String phone, String email) {
        super(id, name, address);

        this.phone = phone;
        this.email = email;
        this.pamNimals = new ArrayList<>();
    }

    public String getFormatedPhone() {
        StringBuilder sb = new StringBuilder(phone);
        sb.insert(0, "(").insert(3, ") ").insert(10, "-");
        return sb.toString();
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

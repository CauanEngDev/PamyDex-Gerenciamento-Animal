package org.example.model;

import java.util.UUID;

public class PamNimal implements Identifiable {
    private UUID id;
    private String name;
    private String specie;
    private String breed;
    private int age;
    private String sex;
    private String currentStatus;
    private PamGym pamGym;
    private PamMaster pamMaster;

    public PamNimal() {}

    public PamNimal(UUID id, String name, String specie, String breed, int age,
                    String sex, String currentStatus, PamGym pamGym, PamMaster pamMaster) {
        this.id = id;
        this.name = name;
        this.specie = specie;
        this.breed = breed;
        this.age = age;
        this.sex = sex;
        this.currentStatus = currentStatus;
        this.pamGym = pamGym;
        this.pamMaster = pamMaster;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecie() {
        return specie;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getStatus(){
        return currentStatus;
    }

    public PamGym getPamGym(){
        return pamGym;
    }

    public PamMaster getPamMaster(){
        return pamMaster;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public void setPamGym(PamGym pamGym) {
        if (this.pamGym == null) {
            this.pamGym = pamGym;
        }
    }

    public void setPamMaster(PamMaster pamMaster) {
        this.pamMaster = pamMaster;
    }
}
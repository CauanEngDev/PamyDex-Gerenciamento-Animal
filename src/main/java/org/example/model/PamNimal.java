package org.example.model;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class PamNimal implements Identifiable {
    private UUID id;
    private String name;
    private String specie;
    private String breed;
    private int age;
    private String sex;
    private String currentStatus;
    private UUID pamGymId;
    private UUID pamMasterId;

    public PamNimal() {}

    /**
     * Construtor da classe
     * @param id id único imutável
     * @param name Nome do PamNimal
     * @param specie Espécie do PamNimal
     * @param breed Raça do PamNimal
     * @param age Idade do PamNimal
     * @param sex Sexo do PamNimal
     * @param currentStatus Status atual do PamNimal
     * @param pamGymId Id da PamGym pra saber ao qual ele está atrelado
     * @param pamMasterId Id do(a) PamMaster pra saber qual ele está atrelado
     */
    public PamNimal(UUID id, String name, String specie, String breed, int age,
                    String sex, String currentStatus, UUID pamGymId, UUID pamMasterId) {
        this.id = id;
        this.name = name;
        this.specie = specie;
        this.breed = breed;
        this.age = age;
        this.sex = sex;
        this.currentStatus = currentStatus;
        this.pamGymId = pamGymId;
        this.pamMasterId = pamMasterId;
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

    public UUID getPamGym(){
        return pamGymId;
    }

    public UUID getPamMaster(){
        return pamMasterId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
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

    public void setPamGym(UUID newPamGym) {
            this.pamGymId = newPamGym;
    }

    public void setPamMaster(UUID newPamMaster) {
        this.pamMasterId = newPamMaster;
    }
}
package org.example.model;

import java.util.UUID;

public abstract class Register implements Identifiable {
    protected UUID id;
    protected String name;
    protected Address address;

    public Register(){}

    public Register(UUID id, String name, Address address){
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }
}

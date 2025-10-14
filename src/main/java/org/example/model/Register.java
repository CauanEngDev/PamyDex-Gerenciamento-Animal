package org.example.model;

import java.util.UUID;

/**
 * Classe abstrata com os parâmetros id, name e address
 * Leva uma implementação para garantir que todas as heranças tenham getId
 */
public abstract class Register implements Identifiable {
    protected UUID id;
    protected String name;
    protected Address address;

    public Register(){}

    /**
     * Construtor da classe
     * @param id id único imutável
     * @param name nome
     * @param address endereço
     */
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

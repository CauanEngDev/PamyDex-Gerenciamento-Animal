package org.example.model;

/**
 * Classe de endereço
 */
public class Address {
    private String neighborhood;
    private String city;
    private String state;

    /**
     * Construtor vazio para o XStream conseguir fazer a conversão da classe na serialização e desserialização
     */
    public Address() {}

    /**
     * Construtor da classe
     * @param neighborhood Bairro
     * @param city cidade
     * @param state Estado
     */
    public Address(String neighborhood, String city, String state) {
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }
}

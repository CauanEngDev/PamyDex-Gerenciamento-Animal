package org.example.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testes para a classe de modelo Address.
 */
class AddressTest {

    @Test
    @DisplayName("Deve construir e atribuir todos os campos corretamente")
    void testConstructorAndGetters() {
        Address address = new Address("Viridian", "Kanto", "Indigo");

        assertEquals("Viridian", address.getNeighborhood());
        assertEquals("Kanto", address.getCity());
        assertEquals("Indigo", address.getState());
    }

    @Test
    @DisplayName("Setters devem atualizar os valores dos atributos corretamente")
    void testSetters() {
        Address address = new Address();

        address.setNeighborhood("Lavender Town");
        address.setCity("Kanto");
        address.setState("Indigo");

        assertEquals("Lavender Town", address.getNeighborhood());
        assertEquals("Kanto", address.getCity());
        assertEquals("Indigo", address.getState());
    }
}
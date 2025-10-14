package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes para a classe de modelo PamGym.
 */
class PamGymTest {

    private PamGym pamGym;
    private final UUID gymId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        Address address = new Address("Pewter", "Kanto", "Indigo");
        pamGym = new PamGym(gymId, "Pewter Gym", address);
    }

    @Test
    @DisplayName("Deve inicializar os atributos corretamente através do construtor")
    void testConstructorAndGetters() {
        assertEquals(gymId, pamGym.getId());
        assertEquals("Pewter Gym", pamGym.getName());
    }

    @Test
    @DisplayName("Deve adicionar e remover um PamNimal da lista interna")
    void testAddAndRemovePamNimal() {
        PamNimal onix = new PamNimal();

        pamGym.addPamNimal(onix);
        List<PamNimal> nimals = pamGym.getPamNimals();
        assertEquals(1, nimals.size());
        assertTrue(nimals.contains(onix));

        pamGym.removePamNimal(onix);
        nimals = pamGym.getPamNimals();
        assertTrue(nimals.isEmpty());
    }

    @Test
    @DisplayName("getPamNimals deve retornar uma cópia defensiva da lista")
    void testGetPamNimalsReturnsDefensiveCopy() {
        PamNimal geodude = new PamNimal();
        pamGym.addPamNimal(geodude);

        List<PamNimal> list1 = pamGym.getPamNimals();
        list1.clear(); // Tentativa de modificar a lista externa

        List<PamNimal> list2 = pamGym.getPamNimals();
        assertEquals(1, list2.size(), "A lista interna do PamGym não deve ser alterada externamente.");
    }
}
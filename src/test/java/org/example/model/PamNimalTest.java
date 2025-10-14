package org.example.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testes para a classe de modelo PamNimal.
 */
class PamNimalTest {

    @Test
    @DisplayName("Deve construir e atribuir todos os campos corretamente")
    void testFullConstructor() {
        UUID id = UUID.randomUUID();
        UUID gymId = UUID.randomUUID();
        UUID masterId = UUID.randomUUID();

        PamNimal bulbasaur = new PamNimal(id, "Bulbasaur", "Seed", "Grass", 1, "M", "AVALAIBLE", gymId, masterId);

        assertEquals(id, bulbasaur.getId());
        assertEquals("Bulbasaur", bulbasaur.getName());
        assertEquals(1, bulbasaur.getAge());
        assertEquals(gymId, bulbasaur.getPamGym());
        assertEquals(masterId, bulbasaur.getPamMaster());
    }

    @Test
    @DisplayName("Setters devem atualizar os valores dos atributos corretamente")
    void testSetters() {
        PamNimal squirtle = new PamNimal();

        squirtle.setName("Squirtle");
        squirtle.setAge(2);

        assertEquals("Squirtle", squirtle.getName());
        assertEquals(2, squirtle.getAge());
    }
}
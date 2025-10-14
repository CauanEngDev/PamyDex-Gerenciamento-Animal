package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes para a classe de modelo PamMaster.
 */
class PamMasterTest {

    private PamMaster pamMaster;
    private final UUID masterId = UUID.randomUUID();
    private final Address address = new Address("Centro", "Pallet", "Kanto");

    @BeforeEach
    void setUp() {
        // Cria uma instância limpa antes de cada teste
        pamMaster = new PamMaster(masterId, "Professor Oak", address, "75982191886", "oak@pokedex.com");
    }

    @Test
    @DisplayName("Deve inicializar os atributos corretamente através do construtor")
    void testConstructorAndGetters() {
        assertEquals(masterId, pamMaster.getId());
        assertEquals("Professor Oak", pamMaster.getName());
        assertEquals(address, pamMaster.getAddress());
        assertEquals("oak@pokedex.com", pamMaster.getEmail());
    }

    @Test
    @DisplayName("Deve formatar o número de telefone corretamente")
    void testGetFormatedPhone() {
        String expectedFormat = "(75) 98219-1886";
        assertEquals(expectedFormat, pamMaster.getFormatedPhone());
    }

    @Test
    @DisplayName("Deve adicionar e remover um PamNimal da lista interna")
    void testAddAndRemovePamNimal() {
        PamNimal pikachu = new PamNimal();

        // Testa a adição
        pamMaster.addPamNimal(pikachu);
        List<PamNimal> nimals = pamMaster.getPamNimals();
        assertEquals(1, nimals.size());
        assertTrue(nimals.contains(pikachu));

        // Testa a remoção
        pamMaster.removePamNimal(pikachu);
        nimals = pamMaster.getPamNimals();
        assertTrue(nimals.isEmpty());
    }

    @Test
    @DisplayName("getPamNimals deve retornar uma cópia defensiva da lista")
    void testGetPamNimalsReturnsDefensiveCopy() {
        PamNimal charmander = new PamNimal();
        pamMaster.addPamNimal(charmander);

        // Pega a lista uma vez
        List<PamNimal> list1 = pamMaster.getPamNimals();
        assertEquals(1, list1.size());

        // Modifica a lista retornada. Isso NÃO DEVE afetar a lista interna do objeto.
        list1.clear();

        // Pega a lista novamente para verificar se a original permaneceu intacta
        List<PamNimal> list2 = pamMaster.getPamNimals();
        assertEquals(1, list2.size(), "A lista interna do PamMaster não deveria ter sido modificada.");
        assertNotSame(list1, list2, "O método deve retornar uma nova instância da lista a cada chamada.");
    }
}
package org.example.controller;

import org.example.commons.Function;
import org.example.commons.IOFunctions;
import org.example.database.Pamydex;
import org.example.model.PamGym;
import org.example.model.PamMaster;
import org.example.model.PamNimal;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Testes de unidade para o PamNimalController, cobrindo criação e remoção.
 */
@DisplayName("Testes do Controlador de PamNimals")
class PamNimalControllerTest {

    private PamNimalController pamNimalController;
    private static MockedStatic<Function> functionMock;
    private static MockedStatic<IOFunctions> ioFunctionsMock;

    @BeforeEach
    void setUp() {
        pamNimalController = new PamNimalController();

        // Simula as dependências estáticas para isolar os testes
        functionMock = mockStatic(Function.class);
        ioFunctionsMock = mockStatic(IOFunctions.class);

        // Limpa as listas antes de cada teste para garantir um ambiente limpo
        Pamydex.PAMNIMALS.clear();
        Pamydex.PAMMASTERS.clear();
        Pamydex.PAMGYMS.clear();
    }

    @AfterEach
    void tearDown() {
        // Libera os mocks após cada teste
        functionMock.close();
        ioFunctionsMock.close();
    }

    // --- Testes de Criação ---

    @Test
    @DisplayName("Deve registrar um novo PamNimal com sucesso")
    void registerPamNimal_ShouldSucceedAndCreateAssociations() {
        // --- PREPARAÇÃO (Arrange) ---
        PamMaster master = new PamMaster(UUID.randomUUID(), "Ash", null, "123", "a@a.com");
        PamGym gym = new PamGym(UUID.randomUUID(), "Pallet Town Gym", null);
        Pamydex.PAMMASTERS.add(master);
        Pamydex.PAMGYMS.add(gym);

        // Simula a geração de um ID único e a escolha do usuário
        functionMock.when(() -> Function.uniqueId(any())).thenReturn(UUID.randomUUID());
        ioFunctionsMock.when(() -> IOFunctions.ask(any())).thenReturn("1"); // Simula o usuário digitando "1"

        // --- AÇÃO (Act) ---
        pamNimalController.registerPamNimal("Pikachu", "Mouse", "Electric", 5, "M", "AVALAIBLE");

        // --- VERIFICAÇÃO (Assert) ---
        assertEquals(1, Pamydex.PAMNIMALS.size(), "A lista principal de PamNimals deve conter 1 animal.");
        assertEquals(1, master.getPamNimals().size(), "A lista de animais do mestre deve conter 1 animal.");
        assertEquals(1, gym.getPamNimals().size(), "A lista de animais do ginásio deve conter 1 animal.");
        assertEquals("Pikachu", Pamydex.PAMNIMALS.get(0).getName());
        functionMock.verify(Function::saveInfo, times(1));
    }

    // --- Testes de Remoção ---

    @Test
    @DisplayName("Deve remover um PamNimal e atualizar todas as listas relacionadas")
    void removePamNimal_ShouldUpdateAllRelatedLists() {
        // --- PREPARAÇÃO (Arrange) ---
        PamMaster master = new PamMaster(UUID.randomUUID(), "Misty", null, "456", "b@b.com");
        PamGym gym = new PamGym(UUID.randomUUID(), "Cerulean Gym", null);
        PamNimal pamNimalToRemove = new PamNimal(
                UUID.randomUUID(), "Staryu", "Star", "Water", 3, "N/A", "AVALAIBLE",
                gym.getId(), master.getId()
        );

        // Simula o estado do programa ANTES da remoção
        master.addPamNimal(pamNimalToRemove);
        gym.addPamNimal(pamNimalToRemove);
        Pamydex.PAMMASTERS.add(master);
        Pamydex.PAMGYMS.add(gym);
        Pamydex.PAMNIMALS.add(pamNimalToRemove);

        // --- AÇÃO (Act) ---
        boolean shouldContinue = pamNimalController.removePamNimal(0);

        // --- VERIFICAÇÃO (Assert) ---
        assertFalse(shouldContinue, "O método deveria retornar false indicando sucesso na remoção.");

        assertTrue(Pamydex.PAMNIMALS.isEmpty(), "A lista principal de PamNimals deve estar vazia.");
        assertTrue(master.getPamNimals().isEmpty(), "A lista de animais do mestre");
    }
}
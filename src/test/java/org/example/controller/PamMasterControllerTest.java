package org.example.controller;

import org.example.commons.Function;
import org.example.database.Pamydex;
import org.example.model.PamMaster;
import org.example.model.PamNimal;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Testes básicos para o PamMasterController.
 */
class PamMasterControllerTest {

    private PamMasterController pamMasterController;
    private static MockedStatic<Function> functionMock;

    @BeforeEach
    void setUp() {
        pamMasterController = new PamMasterController();
        functionMock = mockStatic(Function.class);
        Pamydex.PAMMASTERS.clear();
        Pamydex.PAMNIMALS.clear();
    }

    @AfterEach
    void tearDown() {
        functionMock.close();
    }

    @Test
    @DisplayName("Deve registrar um novo PamMaster com sucesso")
    void testRegisterPamMaster() {
        // --- PREPARAÇÃO (Arrange) ---
        functionMock.when(() -> Function.uniqueId(any())).thenReturn(UUID.randomUUID());

        // --- AÇÃO (Act) ---
        pamMasterController.registerPamMaster("Misty", "456", "b@b.com", "Cerulean", "Kanto", "Indigo");

        // --- VERIFICAÇÃO (Assert) ---
        assertEquals(1, Pamydex.PAMMASTERS.size());
        assertEquals("Misty", Pamydex.PAMMASTERS.get(0).getName());
        functionMock.verify(Function::saveInfo, times(1));
    }

    @Test
    @DisplayName("Deve remover um PamMaster e transferir seus PamNimals")
    void testRemovePamMaster() {
        // --- PREPARAÇÃO (Arrange) ---
        PamMaster masterToRemove = new PamMaster(UUID.randomUUID(), "Jessie", null, "111", "e@e.com");
        PamMaster newMaster = new PamMaster(UUID.randomUUID(), "James", null, "222", "f@f.com");
        PamNimal arbok = new PamNimal(UUID.randomUUID(), "Arbok", "Cobra", "Poison", 8, "M", "INTREATMENT", null, masterToRemove.getId());

        masterToRemove.addPamNimal(arbok);
        Pamydex.PAMMASTERS.add(masterToRemove);
        Pamydex.PAMMASTERS.add(newMaster);
        Pamydex.PAMNIMALS.add(arbok);

        // --- AÇÃO (Act) ---
        pamMasterController.removePamMaster(0, newMaster.getId());

        // --- VERIFICAÇÃO (Assert) ---
        assertEquals(1, Pamydex.PAMMASTERS.size(), "A lista de mestres deve ter apenas 1 item.");
        assertEquals("James", Pamydex.PAMMASTERS.get(0).getName(), "O mestre restante deve ser 'James'.");
        assertEquals(newMaster.getId(), arbok.getPamMaster(), "O ID do mestre do Arbok deve ter sido atualizado.");

        // Verifica a correção do bug: o novo mestre deve ter o animal em sua lista
        assertEquals(1, newMaster.getPamNimals().size(), "A lista de animais do novo mestre deve conter 1 animal.");
        assertEquals("Arbok", newMaster.getPamNimals().get(0).getName());
    }
}
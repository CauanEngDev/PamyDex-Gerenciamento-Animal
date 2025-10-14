package org.example.controller;

import org.example.commons.Function;
import org.example.database.Pamydex;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

/**
 * Testes básicos para o PamGymController.
 */
class PamGymControllerTest {

    private PamGymController pamGymController;
    private static MockedStatic<Function> functionMock;

    @BeforeEach
    void setUp() {
        pamGymController = new PamGymController();
        functionMock = mockStatic(Function.class);
        Pamydex.PAMGYMS.clear();
    }

    @AfterEach
    void tearDown() {
        functionMock.close();
    }

    @Test
    @DisplayName("Deve registrar um novo PamGym com sucesso")
    void testRegisterPamGym() {
        // --- PREPARAÇÃO (Arrange) ---
        functionMock.when(() -> Function.uniqueId(any())).thenReturn(UUID.randomUUID());

        // --- AÇÃO (Act) ---
        pamGymController.registerPamGym("Pewter Gym", "Pewter", "Kanto", "Indigo");

        // --- VERIFICAÇÃO (Assert) ---
        assertEquals(1, Pamydex.PAMGYMS.size());
        assertEquals("Pewter Gym", Pamydex.PAMGYMS.get(0).getName());
        functionMock.verify(Function::saveInfo, times(1));
    }
}
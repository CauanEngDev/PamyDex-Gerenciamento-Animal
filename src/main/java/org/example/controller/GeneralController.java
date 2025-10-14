package org.example.controller;

/**
 * Classe que instacia de forma estática os controller de PamNimals, PamGyms e PamMasters
 */
public class GeneralController {
    public static final PamGymController gymController = new PamGymController();
    public static final PamNimalController animalController = new PamNimalController();
    public static final PamMasterController masterController = new PamMasterController();
}

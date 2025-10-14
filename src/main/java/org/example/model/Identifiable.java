package org.example.model;

import java.util.UUID;

/**
 * Interface para garantir que PamNimal, PamMasters e PamGyms tenham um getId e
 * a função de criação de id funcionar sem passar exatamente o tipo de instancia
 */
public interface Identifiable {
    UUID getId();
}

package com.utm.backend_api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Prioridad {
    ALTA("Alta"),
    MEDIA("Media"),
    BAJA("Baja");

    private final String label;

    Prioridad(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static Prioridad fromLabel(String label) {
        for (Prioridad prioridad : values()) {
            if (prioridad.label.equalsIgnoreCase(label.trim())) {
                return prioridad;
            }
        }
        throw new IllegalArgumentException("Prioridad inválida: " + label);
    }
}

package com.utm.backend_api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Estado {
    ABIERTO("Abierto"),
    EN_PROGRESO("En Progreso"),
    CERRADO("Cerrado");

    private final String label;

    Estado(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static Estado fromLabel(String label) {
        for (Estado estado : values()) {
            if (estado.label.equalsIgnoreCase(label.trim())) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado inválido: " + label);
    }
}

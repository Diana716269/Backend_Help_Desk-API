package com.utm.backend_api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Categoria {
    RED("Red"),
    HARDWARE("Hardware"),
    SOFTWARE("Software");

    private final String label;

    Categoria(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static Categoria fromLabel(String label) {
        for (Categoria categoria : values()) {
            if (categoria.label.equalsIgnoreCase(label.trim())) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Categoría inválida: " + label);
    }
}

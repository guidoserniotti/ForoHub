package com.aluracursos.forohub.domain.topic;

public enum Status {
    OPEN("Abierto"),
    CLOSED("Cerrado"),
    SOLVED("Solucionado"),
    UNANSWERED("Sin respuesta");

    private final String spanishAlias;

    Status(String spanishAlias) {
        this.spanishAlias = spanishAlias;
    }

    public String getSpanishAlias() {
        return spanishAlias;
    }
}

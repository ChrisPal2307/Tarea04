package org.example;

public class Incidente {
    private String descripcion;
    private boolean resuelto;

    public Incidente(String descripcion) {
        validarDescripcion(descripcion);
        this.descripcion = descripcion;
        this.resuelto = false;
    }

    private void validarDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía.");
        }
        if (descripcion.split(" ").length < 15) {
            throw new IllegalArgumentException("La descripción debe tener al menos 15 palabras.");
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean estaResuelto() {
        return resuelto;
    }

    public void resolver() {
        this.resuelto = true;
    }
}


package org.example;

import org.example.ChainOfResponsability.TipoSolicitud;

public class Incidente {
    private TipoSolicitud descripcion;
    private boolean resuelto;

    public Incidente(TipoSolicitud descripcion) {
        this.descripcion = descripcion;
        this.resuelto = false;
    }

    public TipoSolicitud getDescripcion() {
        return descripcion;
    }

    public boolean estaResuelto() {
        return resuelto;
    }

    public void resolver() {
        this.resuelto = true;
    }
}


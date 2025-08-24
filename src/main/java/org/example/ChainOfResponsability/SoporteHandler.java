package org.example.ChainOfResponsability;

public abstract class SoporteHandler {
    protected SoporteHandler siguiente;

    public void setSiguiente(SoporteHandler siguiente) {
        this.siguiente = siguiente;
    }

    public abstract void manejarSolicitud(String solicitud);
}

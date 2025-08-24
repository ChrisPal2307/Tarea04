package org.example.ChainOfResponsability;

public class AgenteSoporte extends SoporteHandler {
    String correo;
    String nombre;

    public AgenteSoporte(String nombre, String correo) {
        this.correo = correo;
        this.nombre = nombre;
    }
    @Override
    public void manejarSolicitud(String solicitud) {
        if (solicitud.equalsIgnoreCase("Problema de pago")) {
            System.out.println("Soporte de agente: El agente está resolviendo el problema");
        } else if (siguiente != null) {
            siguiente.manejarSolicitud(solicitud);
        }
    }

}

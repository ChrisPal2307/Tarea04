package org.example.ChainOfResponsability;

public class AgenteSoporte extends SoporteHandler {
    String correo;
    String nombre;

    public AgenteSoporte(String nombre, String correo) {
        this.correo = correo;
        this.nombre = nombre;
    }
    @Override
    public void manejarSolicitud(TipoSolicitud solicitud) {
        if (solicitud == TipoSolicitud.PROBLEMA_PAGO) {
            System.out.println("Soporte de agente: El agente está resolviendo el problema");
        } else if (siguiente != null) {
            siguiente.manejarSolicitud(solicitud);
        }
    }

}

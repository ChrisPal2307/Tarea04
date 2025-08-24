package org.example.ChainOfResponsability;

public class DepartamentoAdministracion extends SoporteHandler {

    @Override
    public void manejarSolicitud(String solicitud) {

        if (solicitud.equalsIgnoreCase("Error persistente")) {
            System.out.println("El departamento de Administración está resolviendo el problema");
        } else if (siguiente != null) {
            siguiente.manejarSolicitud(solicitud);
        }
    }
}

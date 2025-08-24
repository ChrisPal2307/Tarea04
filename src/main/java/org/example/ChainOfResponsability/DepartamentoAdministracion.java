package org.example.ChainOfResponsability;

public class DepartamentoAdministracion extends SoporteHandler {

    @Override
    public void manejarSolicitud(TipoSolicitud solicitud) {

        if (solicitud == TipoSolicitud.OTRO) {
            System.out.println("El departamento de Administración está resolviendo el problema");
        } else if (siguiente != null) {
            siguiente.manejarSolicitud(solicitud);
        }
    }
}

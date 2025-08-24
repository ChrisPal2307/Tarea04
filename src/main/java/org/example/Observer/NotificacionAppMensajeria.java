package org.example.Observer;

public class NotificacionAppMensajeria implements CanalNotificacion {
    @Override
    public void enviar(String destinatario, String mensaje) {
        System.out.println("Enviando mensaje por app a " + destinatario + ": " + mensaje);
    }
}

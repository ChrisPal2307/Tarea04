package org.example.Observer;

public class NotificacionEmail implements CanalNotificacion {
    @Override
    public void enviar(String destinatario, String mensaje) {
        System.out.println("Enviando Email a " + destinatario + ": " + mensaje);
    }
}

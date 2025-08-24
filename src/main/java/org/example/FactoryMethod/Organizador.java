package org.example.FactoryMethod;

import org.example.Boleto;
import org.example.EstadoBoleto;
import org.example.Evento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Organizador extends Usuario {

    public Organizador(String nombre, String correo, String telefono) {
        super(nombre, correo, telefono);

        if (nombre == null) throw new IllegalArgumentException("El nombre no puede ser null");
        if (correo == null) throw new IllegalArgumentException("El correo no puede ser null");
        if (telefono == null) throw new IllegalArgumentException("El teléfono no puede ser null");
    }

    public boolean cambioFechaEvento(Evento evento, LocalDate nuevaFecha) {
        List<Cliente> clientes = evento.getClientes();
        boolean todosNotificados = true;
        for (Cliente cliente : clientes) {
            if (cliente.getCanales() == null || cliente.getCanales().isEmpty()) {
                todosNotificados = false;
            } else {
                cliente.recibirNotificacion("El evento '" + evento.getNombre() + "' ha cambiado su fecha a: " + nuevaFecha);
            }
        }
        List<LocalDate> fechas = evento.getFechas();
        if (fechas != null && !fechas.isEmpty()) {
            fechas.clear();
        } else {
            fechas = new ArrayList<>();
        }
        fechas.add(nuevaFecha);
        evento.setFechas(fechas);
        hacerDevoluciones(evento);
        return todosNotificados;
    }



    public boolean cancelacionEvento(Evento evento) {
        List<Cliente> clientes = evento.getClientes();
        if (clientes == null || clientes.isEmpty()) {
            System.out.println("No hay clientes registrados en el evento.");
            return false;
        }
        boolean algunaNotificacionEnviada = false;
        for (Cliente cliente : clientes) {
            if (cliente.getCanales() != null && !cliente.getCanales().isEmpty()) {
                cliente.recibirNotificacion("Se ha cancelado el evento");
                algunaNotificacionEnviada = true;
            } else {
                System.out.println("Cliente " + cliente.getNombre() + " no tiene canales de notificación configurados.");
            }
        }
        if (algunaNotificacionEnviada) {
            hacerDevoluciones(evento);
        } else {
            System.out.println("No se realizaron devoluciones porque no hubo notificaciones enviadas.");
        }
        return algunaNotificacionEnviada;
    }



    public boolean hacerDevoluciones(Evento evento) {
        List<Cliente> clientes = evento.getClientes();
        boolean devolucionRealizada = false;
        for (Cliente cliente : clientes) {
            double totalDevolucion = 0.0;
            List<Boleto> boletosADevolver = cliente.getBoletosComprados();
            if (boletosADevolver != null && !boletosADevolver.isEmpty()) {
                for (Boleto b : boletosADevolver) {
                    totalDevolucion += b.getPrecio();
                    b.setEstado(EstadoBoleto.CANCELADO);
                }
                boletosADevolver.clear();
                System.out.println("Devolviendo $" + totalDevolucion + " a cliente " + cliente.getNombre());
                devolucionRealizada = true;
            } else {
                System.out.println("No hay boletos para devolver a cliente " + cliente.getNombre());
            }
        }
        return devolucionRealizada;
    }

}


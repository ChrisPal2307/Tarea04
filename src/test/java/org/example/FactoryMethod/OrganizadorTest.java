package org.example.FactoryMethod;

import org.example.*;
import org.example.Observer.CanalNotificacion;
import org.example.Observer.NotificacionEmail;
import org.example.Observer.NotificacionSMS;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrganizadorTest {

    @Test
    void constructor_NombreNull_LanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Organizador(null, "correo@test.com", "123456");
        });
    }

    @Test
    void constructor_CorreoNull_LanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Organizador("Juan", null, "123456");
        });
    }

    @Test
    void constructor_TelefonoNull_LanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Organizador("Juan", "correo@test.com", null);
        });
    }

    @Test
    void cambioFechaEvento_Sin_canales_Notificacion() {
        Evento evento = new Evento("Concierto", "Artista X", "Rock");
        
        List<FechaEvento> fechasOriginales = new ArrayList<>();
        
        fechasOriginales.add(new FechaEvento(LocalDate.of(2025, 8, 15),new Ubicacion("Parque Samanes", "Guayaquil","Ecuador",-2.10, -79.89)));
        fechasOriginales.add(new FechaEvento(LocalDate.of(2025, 8, 16),new Ubicacion("Parque Samanes", "Guayaquil","Ecuador",-2.10, -79.89)));
        
        evento.setFechas(fechasOriginales);

        Cliente cliente1 = new Cliente("Ana", "ana@test.com", "123");
        Cliente cliente2 = new Cliente("Luis", "luis@test.com", "456");
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente1);
        clientes.add(cliente2);
        evento.setClientes(clientes);

        Organizador organizador = new Organizador("Org", "org@test.com", "789");
        FechaEvento nuevaFecha = new FechaEvento(LocalDate.of(2025, 9, 1),new Ubicacion("Parque Samanes", "Guayaquil","Ecuador",-2.10, -79.89));
        assertFalse(organizador.cambioFechaEvento(evento, nuevaFecha));
    }

    @Test
    void cambioFechaEvento_ConCanales_Notificacion() {
        Evento evento = new Evento("Concierto", "Artista X", "Rock");

        List<FechaEvento> fechasOriginales = new ArrayList<>();
        
        fechasOriginales.add(new FechaEvento(LocalDate.of(2025, 8, 15),new Ubicacion("Parque Samanes", "Guayaquil","Ecuador",-2.10, -79.89)));
        fechasOriginales.add(new FechaEvento(LocalDate.of(2025, 8, 16),new Ubicacion("Parque Samanes", "Guayaquil","Ecuador",-2.10, -79.89)));
        
        evento.setFechas(fechasOriginales);

        Cliente cliente1 = new Cliente("Ana", "ana@test.com", "123");
        Cliente cliente2 = new Cliente("Luis", "luis@test.com", "456");

        cliente1.setCanales(List.of(new NotificacionEmail()));
        cliente2.setCanales(List.of(new NotificacionSMS()));

        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente1);
        clientes.add(cliente2);
        evento.setClientes(clientes);

        Organizador organizador = new Organizador("Org", "org@test.com", "789");
        FechaEvento nuevaFecha = new FechaEvento(LocalDate.of(2025, 9, 1),new Ubicacion("Parque Samanes", "Guayaquil","Ecuador",-2.10, -79.89));

        assertTrue(organizador.cambioFechaEvento(evento, nuevaFecha));
    }

    @Test
    void testHacerDevoluciones_ConBoletosComprados() {
        Evento evento = new Evento("Concierto", "Artista X", "Rock");
        Cliente cliente = new Cliente("Ana", "ana@test.com", "123");
        Boleto boleto1 = new Boleto("B001", TipoBoleto.GENERAL, new Precio(100.0, "USD"));
        Boleto boleto2 = new Boleto("B002", TipoBoleto.VIP, new Precio(200.0, "USD"));
        boleto1.setEstado(EstadoBoleto.VENDIDO);
        boleto2.setEstado(EstadoBoleto.VENDIDO);
        List<Boleto> boletosComprados = new ArrayList<>();
        boletosComprados.add(boleto1);
        boletosComprados.add(boleto2);
        cliente.setBoletosComprados(boletosComprados);
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente);
        evento.setClientes(clientes);
        Organizador organizador = new Organizador("Org", "org@test.com", "789");
        boolean resultado = organizador.hacerDevoluciones(evento);
        assertTrue(resultado);
    }

    @Test
    void testHacerDevoluciones_SinBoletosComprados() {
        Evento evento = new Evento("Concierto", "Artista X", "Rock");
        Cliente cliente = new Cliente("Ana", "ana@test.com", "123");
        Boleto boleto1 = new Boleto("B001", TipoBoleto.GENERAL, new Precio(100.0, "USD"));
        Boleto boleto2 = new Boleto("B002", TipoBoleto.VIP, new Precio(200.0, "USD"));
        boleto1.setEstado(EstadoBoleto.VENDIDO);
        boleto2.setEstado(EstadoBoleto.VENDIDO);
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente);
        evento.setClientes(clientes);
        Organizador organizador = new Organizador("Org", "org@test.com", "789");
        boolean resultado = organizador.hacerDevoluciones(evento);
        assertFalse(resultado);
    }

    @Test
    void testCancelacionEvento_SinCanales() {
        Evento evento = new Evento("Concierto", "Artista X", "Rock");
        Cliente cliente1 = new Cliente("Ana", "ana@test.com", "123");
        Cliente cliente2 = new Cliente("Luis", "luis@test.com", "456");
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente1);
        clientes.add(cliente2);
        evento.setClientes(clientes);
        Organizador organizador = new Organizador("Org", "org@test.com", "789");
        boolean resultado = organizador.cancelacionEvento(evento);
        assertFalse(resultado);
    }


    @Test
    void testCancelacionEvento_ConCanales() {
        Evento evento = new Evento("Concierto", "Artista X", "Rock");
        Cliente cliente1 = new Cliente("Ana", "ana@test.com", "123");
        Cliente cliente2 = new Cliente("Luis", "luis@test.com", "456");

        List<CanalNotificacion> canales = new ArrayList<>();
        canales.add(new NotificacionEmail());
        cliente2.setCanales(canales);

        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente1);
        clientes.add(cliente2);
        evento.setClientes(clientes);

        Organizador organizador = new Organizador("Org", "org@test.com", "789");

        boolean resultado = organizador.cancelacionEvento(evento);
        assertTrue(resultado);
    }

    @Test
    void testCancelacionEvento_SinClientes() {
        Evento evento = new Evento("Concierto", "Artista X", "Rock");
        evento.setClientes(new ArrayList<>());
        Organizador organizador = new Organizador("Org", "org@test.com", "789");
        boolean resultado = organizador.cancelacionEvento(evento);
        assertFalse(resultado);
    }
}
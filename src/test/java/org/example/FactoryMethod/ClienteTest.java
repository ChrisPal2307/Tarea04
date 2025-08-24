package org.example.FactoryMethod;

import org.example.Boleto;
import org.example.Evento;
import org.example.Incidente;
import org.example.Observer.CanalNotificacion;
import org.example.Observer.NotificacionEmail;
import org.example.Observer.NotificacionSMS;
import org.example.Strategy.MetodoPago;
import org.example.Strategy.PagoTarjetaCredito;
import org.example.TipoBoleto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void constructor_NombreNull_LanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente(null, "correo@test.com", "123456");
        });
    }
    @Test
    void constructor_CorreoNull_LanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("Juan", null, "123456");
        });
    }

    @Test
    void constructor_TelefonoNull_LanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("Juan", "correo@test.com", null);
        });
    }

    @Test
    void getIncidentes() {
        Cliente cliente = new Cliente("Juan", "juan@test.com", "123456");
        assertTrue(cliente.getIncidentes().isEmpty());
    }

    @Test
    void setIncidentes() {
        Cliente cliente = new Cliente("Juan", "juan@test.com", "123456");
        List<Incidente> lista = new ArrayList<>();
        lista.add(new Incidente("El cliente tuvo un problema al realizar el pago en la plataforma, el sistema no procesó correctamente la transacción y lanzó un error inesperado."));
        cliente.setIncidentes(lista);
        assertEquals(lista, cliente.getIncidentes());
    }

    @Test
    void getBoletosComprados() {
        Cliente cliente = new Cliente("Juan", "juan@test.com", "123456");
        assertTrue(cliente.getBoletosComprados().isEmpty());
    }

    @Test
    void buscarEventos() {
        Evento evento1 = new Evento("Concierto Rock", "Banda X", "Rock");
        Evento evento2 = new Evento("Festival Jazz", "Artista Y", "Jazz");
        List<Evento> listaEventos = Arrays.asList(evento1, evento2);
        Cliente cliente = new Cliente("Juan", "juan@test.com", "123456");
        Evento resultado = cliente.buscarEventos("rock", listaEventos);
        assertEquals(evento1, resultado);
    }

    @Test
    void buscarEventos_NoCoincideDevuelveNull() {
        Evento evento1 = new Evento("Concierto Rock", "Banda X", "Rock");
        Evento evento2 = new Evento("Festival Jazz", "Artista Y", "Jazz");
        List<Evento> listaEventos = Arrays.asList(evento1, evento2);
        Cliente cliente = new Cliente("Juan", "juan@test.com", "123456");
        Evento resultado = cliente.buscarEventos("pop", listaEventos);
        assertNull(resultado);
    }


    @Test
    void reservarBoleto_ReservaExitosa() {
        Evento evento = new Evento("Concierto", "Artista", "Rock");
        Boleto b1 = new Boleto("id1", TipoBoleto.GENERAL, 50.0);
        Boleto b2 = new Boleto("id2", TipoBoleto.GENERAL, 50.0);
        Boleto b3 = new Boleto("id3", TipoBoleto.VIP, 100.0);
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(b1);
        boletos.add(b2);
        boletos.add(b3);
        evento.setBoletos(boletos);
        Cliente cliente = new Cliente("Juan", "juan@test.com", "123456");
        boolean resultado = cliente.reservarBoleto(evento, 2, TipoBoleto.GENERAL);
        assertTrue(resultado);
    }

    @Test
    void reservarBoleto_ReservaNoExitosa() {
        Evento evento = new Evento("Concierto", "Artista", "Rock");
        Boleto b1 = new Boleto("id1", TipoBoleto.GENERAL, 50.0);
        Boleto b2 = new Boleto("id2", TipoBoleto.VIP, 50.0);
        Boleto b3 = new Boleto("id3", TipoBoleto.VIP, 100.0);
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(b1);
        boletos.add(b2);
        boletos.add(b3);
        evento.setBoletos(boletos);
        Cliente cliente = new Cliente("Juan", "juan@test.com", "123456");
        boolean resultado = cliente.reservarBoleto(evento, 2, TipoBoleto.GENERAL);
        assertFalse(resultado);
    }



    @Test
    void setMetodoPago() {
        Cliente cliente = new Cliente("Juan", "juan@test.com", "123456");
        MetodoPago metodo = new PagoTarjetaCredito(); // Por ejemplo
        cliente.setMetodoPago(metodo);
        assertEquals(metodo, cliente.getMetodoPago());
    }


    @Test
    void pagar_Exito() {
        Cliente cliente = new Cliente("Juan", "juan@test.com", "123456");
        MetodoPago metodoPago = new PagoTarjetaCredito();
        cliente.setMetodoPago(metodoPago);
        Boleto b1 = new Boleto("id1", TipoBoleto.GENERAL, 50.0);
        Boleto b2 = new Boleto("id2", TipoBoleto.VIP, 100.0);
        cliente.getBoletosReservados().add(b1);
        cliente.getBoletosReservados().add(b2);
        boolean resultado = cliente.pagar();
        assertTrue(resultado);
    }

    @Test
    void pagar_Sin_Metodo_de_Pago() {
        Cliente cliente = new Cliente("Juan", "juan@test.com", "123456");
        Boleto b1 = new Boleto("id1", TipoBoleto.GENERAL, 50.0);
        Boleto b2 = new Boleto("id2", TipoBoleto.VIP, 100.0);
        cliente.getBoletosReservados().add(b1);
        cliente.getBoletosReservados().add(b2);
        boolean resultado = cliente.pagar();
        assertFalse(resultado);
    }

    @Test
    void pagar_Sin_Boletos_Reservados() {
        Cliente cliente = new Cliente("Juan", "juan@test.com", "123456");
        Boleto b1 = new Boleto("id1", TipoBoleto.GENERAL, 50.0);
        Boleto b2 = new Boleto("id2", TipoBoleto.VIP, 100.0);
        boolean resultado = cliente.pagar();
        assertFalse(resultado);
    }



    @Test
    void getBoletosReservados() {
        Cliente cliente = new Cliente("Ana", "ana@test.com", "987654");
        Boleto boleto = new Boleto("id1", TipoBoleto.GENERAL, 30.0);
        cliente.getBoletosReservados().add(boleto);
        List<Boleto> reservados = cliente.getBoletosReservados();
        assertTrue(reservados.contains(boleto));
    }

    @Test
    void getNombre() {
        Cliente cliente = new Cliente("Juan", "juan@test.com", "123456");
        assertEquals("Juan", cliente.getNombre());
    }


    @Test
    void setCanales() {
        Cliente cliente = new Cliente("Ana", "ana@test.com", "987654");
        List<CanalNotificacion> canales = new ArrayList<>();
        canales.add(new NotificacionEmail());
        canales.add(new NotificacionSMS());
        cliente.setCanales(canales);
        assertEquals(canales, cliente.getCanales());
    }
}
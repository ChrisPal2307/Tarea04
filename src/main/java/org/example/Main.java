package org.example;

import org.example.ChainOfResponsability.AgenteSoporte;
import org.example.ChainOfResponsability.DepartamentoAdministracion;
import org.example.ChainOfResponsability.SoporteHandler;
import org.example.Observer.CanalNotificacion;
import org.example.Observer.NotificacionAppMensajeria;
import org.example.Observer.NotificacionEmail;
import org.example.Observer.NotificacionSMS;
import org.example.Strategy.MetodoPago;
import org.example.Strategy.PagoMovil;
import org.example.Strategy.PagoTarjetaCredito;
import org.example.Strategy.PagoTransferenciaElectronica;
import org.example.FactoryMethod.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        //Ubicaciones
        ArrayList<Ubicacion> ubicacionesEvento1 = new ArrayList<>();
        ubicacionesEvento1.add(new Ubicacion("Av. Principal 123", "Quito", "Ecuador", -0.18, -78.46));
        ubicacionesEvento1.add(new Ubicacion("Calle 45", "Guayaquil", "Ecuador", -2.18, -79.89));
        ArrayList<Ubicacion> ubicacionesEvento2 = new ArrayList<>();
        ubicacionesEvento2.add(new Ubicacion("Teatro Nacional", "Quito", "Ecuador", -0.21, -78.50));
        //Fechas de eventos
        ArrayList<LocalDate> fechasEvento1 = new ArrayList<>();
        fechasEvento1.add(LocalDate.of(2025, 9, 12));
        fechasEvento1.add(LocalDate.of(2025, 9, 13));
        ArrayList<LocalDate> fechasEvento2 = new ArrayList<>();
        fechasEvento2.add(LocalDate.of(2025, 10, 5));
        //Eventos
        Evento evento1 = new Evento("Rock Fest", "The Rockers", "Rock");
        evento1.setUbicaciones(ubicacionesEvento1);
        evento1.setFechas(fechasEvento1);
        Evento evento2 = new Evento("Jazz Night", "Smooth Jazz Band", "Jazz");
        evento2.setUbicaciones(ubicacionesEvento2);
        evento2.setFechas(fechasEvento2);

        //Organizadores de eventos
        Organizador o1 = new Organizador("Pedro", "pedrito@gmail.com", "0912122344");
        evento1.setOrganizador(o1);
        Organizador o2 = new Organizador("Juliana", "juli@gmail.com", "0912166844");
        evento1.setOrganizador(o2);

        //Boletos evento1
        ArrayList<Boleto> boletosEvento1 = new ArrayList<>();
        boletosEvento1.add(new Boleto("B001", TipoBoleto.GENERAL, 50.0));
        boletosEvento1.add(new Boleto("B002", TipoBoleto.VIP, 120.0));
        boletosEvento1.add(new Boleto("B003", TipoBoleto.ASIENTO_RESERVADO, 80.0));
        evento1.setBoletos(boletosEvento1);
        //Boletos evento2
        ArrayList<Boleto> boletosEvento2 = new ArrayList<>();
        boletosEvento1.add(new Boleto("C001", TipoBoleto.GENERAL, 50.0));
        boletosEvento1.add(new Boleto("C002", TipoBoleto.VIP, 120.0));
        boletosEvento1.add(new Boleto("C003", TipoBoleto.ASIENTO_RESERVADO, 80.0));
        evento2.setBoletos(boletosEvento2);

        List<Evento> eventosDisponibles = new ArrayList<>();
        eventosDisponibles.add(evento1);
        eventosDisponibles.add(evento2);

        //UsuarioCliente busca el evento
        UserCreator uc1 = new ClienteCreator();
        Cliente c1 = (Cliente) uc1.crearUsuario("Juan", "juanito@outlook.com", "0925654111");
        Evento evento_cliente1 = c1.buscarEventos("Rock Fest", eventosDisponibles);
        System.out.println(evento_cliente1.toString());

        //UsuarioCliente reserva boleto del evento seleccionado
        c1.reservarBoleto(evento_cliente1,1,TipoBoleto.VIP);

        //Metodos de pago Disponibles
        MetodoPago pagoTarjeta = new PagoTarjetaCredito();
        MetodoPago pagoTransferencia  = new PagoTransferenciaElectronica();
        MetodoPago pagoMovil = new PagoMovil();

        //Cliente define su método de pago
        c1.setMetodoPago(pagoTarjeta);

        //Cliente realiza el pago correspondiente a los boletos reservados
        c1.pagar();

        //Agrega el cliente asociado al evento en cuestión
        evento1.getClientes().add(c1);

        //Cliente elige los canales en los que desea recibir las notificaciones
        CanalNotificacion canal1 = new NotificacionAppMensajeria();
        CanalNotificacion canal2 = new NotificacionEmail();
        CanalNotificacion canal3 = new NotificacionSMS();

        List<CanalNotificacion> lista_canales_c1 = new ArrayList<>();
        lista_canales_c1.add(canal1);
        lista_canales_c1.add(canal2);
        c1.setCanales(lista_canales_c1);

        //Organizadores cancelan y cambian
        System.out.println("----- Caso en de cancelacion de evento ------");
        o1.cancelacionEvento(evento1);
        System.out.println("----- Caso en de cambio de fecha ------");
        o1.cambioFechaEvento(evento1, LocalDate.of(2025, 12, 10));

        //soporte para reportar los problemas
        SoporteHandler s1 = new AgenteSoporte("Pepe", "pepito@gmail.com");
        SoporteHandler s2= new DepartamentoAdministracion();
        evento1.setAdministracion((DepartamentoAdministracion) s2);
        s1.setSiguiente(s2);

        //Incidentes
        Incidente i1 = new Incidente("Problema de pago");
        Incidente i2 = new Incidente("Error persistente");
        c1.getIncidentes().add(i1);
        c1.getIncidentes().add(i2);

        //Problema con el pago
        s1.manejarSolicitud(i1.getDescripcion());
        //Problema persistente
        s2.manejarSolicitud(i2.getDescripcion());
    }
}
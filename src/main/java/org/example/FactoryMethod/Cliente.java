package org.example.FactoryMethod;

import org.example.*;
import org.example.Observer.CanalNotificacion;
import org.example.Strategy.MetodoPago;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
    private List<Boleto> boletosReservados;
    private MetodoPago metodoPago;
    private List<CanalNotificacion> canales;
    private List<Boleto> boletosComprados;
    private List<Incidente> incidentes;

    public Cliente(String nombre, String correo, String telefono) {
        super(nombre, correo, telefono);
        if (nombre == null) throw new IllegalArgumentException("El nombre no puede ser null");
        if (correo == null) throw new IllegalArgumentException("El correo no puede ser null");
        if (telefono == null) throw new IllegalArgumentException("El teléfono no puede ser null");
        this.boletosReservados = new ArrayList<>();
        this.boletosComprados = new ArrayList<>();
        this.incidentes = new ArrayList<>();
        this.canales = new ArrayList<>();
    }

    public List<Incidente> getIncidentes(){
        return incidentes;
    }

    public void setIncidentes(List<Incidente> incidentes){
        this.incidentes = incidentes;
    }

    public List<Boleto> getBoletosComprados(){
        return boletosComprados;
    }

    public Evento buscarEventos(String criterio, List<Evento> todosLosEventos) {
        String criterioLower = criterio.toLowerCase();
        for (Evento evento : todosLosEventos) {
            if (evento.toString().toLowerCase().contains(criterioLower)) {
                return evento;
            }
        }
        return null;
    }


    public boolean reservarBoleto(Evento evento, int cantidad, TipoBoleto tipo) {
        List<Boleto> disponibles = new ArrayList<>();

        // Buscar boletos disponibles del tipo solicitado
        for (Boleto b : evento.getBoletosDisponibles()) {
            if (b.getTipo() == tipo && b.getEstado() == EstadoBoleto.DISPONIBLE) {
                disponibles.add(b);
                if (disponibles.size() == cantidad) {
                    break;
                }
            }
        }

        if (disponibles.size() < cantidad) {
            // No hay suficientes boletos disponibles
            return false;
        }

        // Reservar los boletos
        for (Boleto b : disponibles) {
            b.addListener((boleto) -> {
                // Notificar al cliente sobre el cambio de estado del boleto
                recibirNotificacion("El estado de su boleto con ID " + boleto.getId() + " ha cambiado a " + boleto.getEstado());
            });

            b.setEstado(EstadoBoleto.RESERVADO);
            boletosReservados.add(b);
        }
        return true;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public void realizarPago(double monto) {
        recibirNotificacion("Se ha realizado un pago de " + monto);
        metodoPago.pagar(monto);
    }

    public boolean checkReservas() {
    	if (boletosReservados == null || boletosReservados.isEmpty()) {
            System.out.println("No hay boletos reservados para pagar.");
            return false;
        }
    	return true;
    }
    
    public boolean checkPago() {
    	if (metodoPago == null) {
            System.out.println("No se ha seleccionado un método de pago.");
            return false;
        }
    	return true;
    }
    
    public boolean pagar() {
        
        if (this.checkReservas() && this.checkPago()) {
        	
        	double total = 0.0;
        	for (Boleto b : boletosReservados) {
        		total += b.getPrecio().getValor();
        	}
        	
        	realizarPago(total);
        	
        	for (Boleto b : boletosReservados) {
        		b.setEstado(EstadoBoleto.VENDIDO);
        		boletosComprados.add(b);
        	}
        	
        	boletosReservados.clear();
        	return true;
        }
        
        return false;
    }

    public List<Boleto> getBoletosReservados(){
        return boletosReservados;
    }

    public String getNombre(){
        return nombre;
    }

    public void recibirNotificacion(String mensaje) {
        for (CanalNotificacion cn : canales){
            cn.enviar(nombre, mensaje);
        }
    }

    public void setCanales(List<CanalNotificacion> canales){
        this.canales = canales;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public List<CanalNotificacion> getCanales() {
        return canales;
    }

    public void setBoletosComprados(List<Boleto> boletosComprados) {
        this.boletosComprados = boletosComprados;
    }
}
package org.example;

import org.example.ChainOfResponsability.DepartamentoAdministracion;
import org.example.FactoryMethod.Cliente;
import org.example.FactoryMethod.Organizador;
 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.example.Observer.Observable;
import java.util.stream.Collectors;

public class Evento extends Observable<Evento> {
    private String nombre;
    private String artista;
    private String tipoMusica;
    private List<Ubicacion> ubicaciones;
    private List<LocalDate> fechas;
    private List<Boleto> boletos;
    private List<Cliente> clientes;
    private Organizador organizador;
    private DepartamentoAdministracion administracion;

    public Evento(String nombre, String artista, String tipoMusica) {
        this.nombre = nombre;
        this.artista = artista;
        this.tipoMusica = tipoMusica;
        this.clientes = new ArrayList<>();
        this.boletos = new ArrayList<>();
        this.ubicaciones = new ArrayList<>();
        this.fechas = new ArrayList<>();
    }

    public List<Boleto> getBoletosDisponibles() {
        return boletos.stream()
                .filter(b -> b.getEstado() == EstadoBoleto.DISPONIBLE)
                .collect(Collectors.toList());
    }

    public void cancelarEvento() {
        // Lógica para cancelar el evento
    }

    public String getNombre(){
        return nombre;
    }

    public List<LocalDate> getFechas(){
        return fechas;
    }

    public Organizador getOrganizador(){
        return organizador;
    }

    public void setAdministracion(DepartamentoAdministracion d){
        this.administracion = d;
    }

    public void setOrganizador(Organizador organizador) {
        this.organizador = organizador;
    }

    public void setClientes(List<Cliente> clientes){
        this.clientes = clientes;
    }
    public List<Cliente> getClientes(){
        return clientes;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        notifyListeners(this);
        this.ubicaciones = ubicaciones;
    }
    public void setFechas(List<LocalDate> fechas) {
        notifyListeners(this);
        this.fechas = fechas;
    }
    public void setBoletos(List<Boleto> boletos) {
        this.boletos = boletos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Evento: ").append(nombre).append("\n")
                .append("Artista: ").append(artista).append("\n")
                .append("Tipo de música: ").append(tipoMusica).append("\n");

        // Lista de ubicaciones
        sb.append("Ubicaciones:\n");
        addListToStringBuilder(artista, ubicaciones, sb);

        // Lista de fechas
        sb.append("Fechas disponibles:\n");
        addListToStringBuilder(artista, fechas, sb);
        return sb.toString();
    }

    private void addListToStringBuilder(String title, List<?> list, StringBuilder sb) {
        sb.append(title).append("\n");
        if (list != null && !list.isEmpty()) {
            for (Object item : list) {
                sb.append("- ").append(item.toString()).append("\n");
            }
        } else {
            sb.append("- (No hay ").append(title.toLowerCase()).append(" registradas)\n");
        }
    }
}

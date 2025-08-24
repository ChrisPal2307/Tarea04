package org.example;

import org.example.Observer.Observable;

public class Boleto extends Observable<Boleto> {
    private String id;
    private TipoBoleto tipo; // General, VIP, Asiento reservado
    private double precio;
    private EstadoBoleto estado;

    public Boleto(String id, TipoBoleto tipo, double precio) {
        validarPrecio(precio);
        this.id = id;
        this.tipo = tipo;
        this.precio = precio;
        this.estado = EstadoBoleto.DISPONIBLE;
    }

    private void validarPrecio(double precio) {
        if (precio <= 0 || precio > 1000) {
            throw new IllegalArgumentException("Precio no válido");
        }
    }

    public String getId() {
        return id;
    }

    public EstadoBoleto getEstado(){
        return estado;
    }
    public TipoBoleto getTipo(){
        return tipo;
    }
    public void setEstado(EstadoBoleto estadoBoleto) {
        this.estado = estadoBoleto;
        notifyListeners(this);
    }
    public double getPrecio(){
        return precio;
    }

    @Override
    public String toString() {
        return "Boleto{" +
                "id='" + id + '\'' +
                ", tipo=" + tipo +
                ", precio=" + precio +
                ", estado=" + estado +
                '}';
    }
}

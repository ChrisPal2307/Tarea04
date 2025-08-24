package org.example;

import org.example.Observer.Observable;

public class Boleto extends Observable<Boleto> {
    private String id;
    private TipoBoleto tipo; // General, VIP, Asiento reservado
    private Precio precio;
    private EstadoBoleto estado;

    public Boleto(String id, TipoBoleto tipo, Precio precio) {
        precio.validarPrecio();
        this.id = id;
        this.tipo = tipo;
        this.precio = precio;
        this.estado = EstadoBoleto.DISPONIBLE;
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
    public Precio getPrecio(){
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

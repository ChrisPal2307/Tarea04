package org.example.Strategy;

public class PagoTransferenciaElectronica implements MetodoPago {
    @Override
    public void pagar(double monto) {
        System.out.println("Procesando transferencia electrónica por $" + monto);
    }
}


package org.example.Strategy;

public class PagoMovil implements MetodoPago {
    @Override
    public void pagar(double monto) {
        System.out.println("Procesando pago móvil por $" + monto);
    }
}


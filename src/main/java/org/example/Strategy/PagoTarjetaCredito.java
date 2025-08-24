package org.example.Strategy;

public class PagoTarjetaCredito implements MetodoPago {
    @Override
    public void pagar(double monto) {
        System.out.println("Procesando pago con tarjeta de crédito por $" + monto);
    }
}

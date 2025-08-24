package org.example;

public class Precio {
    private final double valor;
    private final String divisa; // ej. "USD", "EUR"

    public Precio(double valor, String divisa) {
        if (valor < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        if (divisa == null || divisa.isEmpty()) {
            throw new IllegalArgumentException("La divisa no puede estar vacía");
        }
        this.valor = valor;
        this.divisa = divisa;
    }

    public double getValor() {
        return valor;
    }

    public String getDivisa() {
        return divisa;
    }

    @Override
    public String toString() {
        return String.format("%s %.2f", divisa, valor);
    }
}

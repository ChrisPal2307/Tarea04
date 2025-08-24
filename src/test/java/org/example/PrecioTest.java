package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrecioTest {

    @Test
    void testConstructorValido() {
        Precio p = new Precio(100, "USD");

        // verificamos que los valores se asignaron bien
        assertEquals(100, p.getValor());
        assertEquals("USD", p.getDivisa());
    }

    @Test
    void testConstructorConValorNegativoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> new Precio(-10, "USD")
        );
    }

    @Test
    void testConstructorConDivisaVaciaLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> new Precio(100, "")
        );
    }

    @Test
    void testValidarPrecioValido() {
        Precio p = new Precio(200, "EUR");
        assertDoesNotThrow(p::validarPrecio);  // no debería lanzar error
    }

    @Test
    void testValidarPrecioCeroLanzaExcepcion() {
        Precio p = new Precio(0, "USD");
        assertThrows(IllegalArgumentException.class, p::validarPrecio);
    }

    @Test
    void testValidarPrecioMayorA1000LanzaExcepcion() {
        Precio p = new Precio(1500, "USD");
        assertThrows(IllegalArgumentException.class, p::validarPrecio);
    }
}
package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class FechaEventoTest {

	Ubicacion ubi = new Ubicacion(
            "Calle Falsa 123",
            "Springfield",
            "USA",
            37.7759,
            -122.4194
    );

    // FE01: Verificar constructor y getters
    @Test
    void FE01_constructorTest() {
        LocalDate fecha = LocalDate.of(2025, 8, 27);
        FechaEvento evento = new FechaEvento(fecha, ubi);

        assertEquals(fecha, evento.getFecha(), "La fecha debería coincidir");
        assertEquals(ubi, evento.getUbi(), "La ubicación debería coincidir");
    }

    // FE02: Verificar formato de toString()
    @Test
    void FE02_toStringTest() {
        LocalDate fecha = LocalDate.of(2025, 8, 27);
        FechaEvento evento = new FechaEvento(fecha, ubi);

        String resultado = evento.toString();

        assertTrue(resultado.contains("Springfield"), "Debe contener la ciudad");
        assertTrue(resultado.contains("USA"), "Debe contener el país");
        assertTrue(resultado.contains("Aug"), "Debe contener el mes abreviado en inglés");
        assertTrue(resultado.contains("2025"), "Debe contener el año");
    }

    // FE03: cuantoFalta con fecha futura
    @Test
    void FE03_cuantoFaltaFuturoTest() {
        LocalDate fechaFutura = LocalDate.now().plusDays(10);
        FechaEvento evento = new FechaEvento(fechaFutura, ubi);

        int dias = evento.cuantoFalta();

        assertTrue(dias > 0, "Debería devolver un número positivo para fecha futura");
    }

    // FE04: cuantoFalta con fecha pasada
    @Test
    void FE04_cuantoFaltaPasadoTest() {
        LocalDate fechaPasada = LocalDate.now().minusDays(5);
        FechaEvento evento = new FechaEvento(fechaPasada, ubi);

        int dias = evento.cuantoFalta();

        assertTrue(dias < 0, "Debería devolver un número negativo para fecha pasada");
    }

    // FE05: cuantoFalta con fecha presente
    @Test
    void FE05_cuantoFaltaPresenteTest() {
        LocalDate fechaPresente = LocalDate.now();
        FechaEvento evento = new FechaEvento(fechaPresente, ubi);

        int dias = evento.cuantoFalta();

        assertEquals(0, dias, "Debería devolver 0 cuando la fecha es hoy");
    }

}

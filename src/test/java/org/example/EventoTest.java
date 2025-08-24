/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.example.ChainOfResponsability.DepartamentoAdministracion;
import org.example.FactoryMethod.Cliente;
import org.example.FactoryMethod.Organizador;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.ParameterizedTest;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author maycmont
 */
public class EventoTest {
    private String nombre = "Concierto de Rock";
    private String artista = "Artista de Prueba";
    private String tipoMusica = "Rock";

    public EventoTest() {
    }

    private Evento crearMockEvento() {
        Evento evento = new Evento(nombre, artista, tipoMusica);
        return evento;
    }

    private Cliente getMockCliente() {
        return new Cliente("Cliente 1", "cliente1@example.com", "123456789");
    }

    private Boleto getMockBoleto(EstadoBoleto estado) {
        Boleto boleto = new Boleto("1", TipoBoleto.GENERAL, 100.0);
        boleto.setEstado(estado);
        return boleto;
    }

    // estado inicial
    @Test
    public void testEstadoInicial() {
        System.out.println("Estado inicial");
        Evento instance = crearMockEvento();
        assertTrue(instance.getClientes().isEmpty());
        assertTrue(instance.getBoletosDisponibles().isEmpty());
        assertTrue(instance.getFechas().isEmpty());
    }

    // sin boletos disponibles
    @ParameterizedTest
    @CsvSource({
        "DISPONIBLE, false",
        "RESERVADO, true",
        "CANCELADO, true"
    })
    public void testSinBoletosDisponibles(EstadoBoleto estado, boolean listaBoletosDisponiblesVacía) {
        System.out.println("Prueba sin boletos disponibles");
        Evento instance = crearMockEvento();
        instance.setBoletos(new ArrayList<>(Arrays.asList(
            getMockBoleto(estado), getMockBoleto(estado)
        )));
        assertEquals(listaBoletosDisponiblesVacía, instance.getBoletosDisponibles().isEmpty());
    }
}

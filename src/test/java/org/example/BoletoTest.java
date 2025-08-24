/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.example;


import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BoletoTest {
    private String id = "1";
    private TipoBoleto tipo = TipoBoleto.GENERAL;
    private double precio = 100.0;


    @Test
    public void testToString() {
        Boleto instance = new Boleto(id, tipo, precio);
        String expResult = "Boleto{id='1', tipo=GENERAL, precio=100.0, estado=DISPONIBLE}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    // precio no válido
    @ParameterizedTest
    @CsvSource({
        "-100.0",
        "0.0",
        "1500.0"
    })
    public void testPrecioNoValido(double precio) {
        System.out.println("testPrecioNoValido");
        assertThrows(IllegalArgumentException.class, () -> {
            Boleto instance = new Boleto(id, tipo, precio);
        });
    }

    // tipo no válido
    @ParameterizedTest
    @CsvSource({
        "INVALIDO",
        "HOLA",
        "null"
    })
    public void testTipoNoValido(String tipo) {
        System.out.println("testTipoNoValido");
        assertThrows(IllegalArgumentException.class, () -> {
            Boleto instance = new Boleto(id, TipoBoleto.valueOf(tipo), precio);
        });
    }
}

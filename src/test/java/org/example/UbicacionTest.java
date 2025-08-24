/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.example;

import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author elmay
 */
public class UbicacionTest {
    
    public UbicacionTest() {
    }

    @ParameterizedTest
    @CsvSource({
        "Calle Falsa 123, Springfield, USA, 37.7749, -122.4194, true",
        "Avenida Siempre Viva 742, Springfield, USA, 37.7749, -122.4194, true",
        "Calle 123, Ciudad, País, 0.0, 0.0, true",
        "Calle Invalida, Ciudad, País, 100.0, 200.0, false"
    })
    public void testConstructorValido(String direccion, String ciudad, String pais, double latitud, double longitud, boolean valido) {
        if (valido) {
            Ubicacion instance = new Ubicacion(direccion, ciudad, pais, latitud, longitud);
            assertEquals(direccion, instance.getDireccion());
            assertEquals(ciudad, instance.getCiudad());
            assertEquals(pais, instance.getPais());
            assertEquals(latitud, instance.getLatitud());
            assertEquals(longitud, instance.getLongitud());
        } else {
            assertThrows(IllegalArgumentException.class, () -> new Ubicacion(direccion, ciudad, pais, latitud, longitud));
        }
    }
}

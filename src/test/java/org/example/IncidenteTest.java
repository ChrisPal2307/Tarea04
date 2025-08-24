/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.example;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.ParameterizedTest;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author elmay
 */
public class IncidenteTest {
    private String validDescription = "Descripción del incidente con más de quince palabras para cumplir con el requisito de indicidente válido.";

    public IncidenteTest() {
    }

    private Incidente getMockIncidente() {
        return new Incidente(validDescription);
    }

    @Test
    public void testEstadoInicialNoResuelto() {
        Incidente instance = getMockIncidente();
        assertFalse(instance.estaResuelto());
    }

    // descripción con cantidad de palabras mínimo(15)
    @ParameterizedTest
    @CsvSource({
        "Descripción del incidente con más de quince palabras para cumplir con el requisito., false",
        "Este es un ejemplo de una descripción que tiene exactamente quince palabras y cumple con el requisito., true",
        "Descripción con menos de quince palabras, false",
        "\"\", false"
    })
    public void testGetDescripcion(String descripcion, boolean valida) {
        System.out.println("getDescripcion");
        if (valida) {
            Incidente instance = new Incidente(descripcion);
            assertEquals(descripcion, instance.getDescripcion());
        } else {
            assertThrows(IllegalArgumentException.class, () -> new Incidente(descripcion));
        }
    }

    @Test
    public void testEstaResuelto() {
        Incidente instance = getMockIncidente();
        assertFalse(instance.estaResuelto());
        instance.resolver();
        assertTrue(instance.estaResuelto());
    }
}

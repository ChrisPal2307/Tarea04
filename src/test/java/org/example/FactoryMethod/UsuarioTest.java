package org.example.FactoryMethod;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void constructor_NombreNull_LanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Usuario(null, "correo@test.com", "123456") {
            };
        });
    }

    @Test
    void constructor_CorreoNull_LanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("Juan", null, "123456") {
            };
        });
    }

    @Test
    void constructor_TelefonoNull_LanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("Juan", "correo@test.com", null) {
            };
        });
    }

    @Test
    void toString_DevuelveFormatoCorrecto() {
        Usuario usuario = new Usuario("Ana", "ana@test.com", "123456") { };
        String esperado = "Usuario{nombre='Ana', correo='ana@test.com', telefono='123456'}";
        assertEquals(esperado, usuario.toString());
    }
}
package org.example.FactoryMethod;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteCreatorTest {

    @Test
    void crearUsuario_Valido_CreaCliente() {
        ClienteCreator creator = new ClienteCreator();
        Usuario usuario = creator.crearUsuario("Luis", "luis@test.com", "987654");
        assertTrue(usuario instanceof Cliente);
    }
}
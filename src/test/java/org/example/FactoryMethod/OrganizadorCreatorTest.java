package org.example.FactoryMethod;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizadorCreatorTest {

    @Test
    void crearUsuario_Valido_CreaOrganizador() {
        OrganizadorCreator creator = new OrganizadorCreator();
        Usuario usuario = creator.crearUsuario("Ana", "ana@test.com", "123456");
        assertTrue(usuario instanceof Organizador);
    }

}
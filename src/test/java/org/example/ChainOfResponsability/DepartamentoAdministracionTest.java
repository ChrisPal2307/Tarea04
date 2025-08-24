package org.example.ChainOfResponsability;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartamentoAdministracionTest {
    @Test
    void testCadenaYUltimoNoTieneSiguiente() {
        AgenteSoporte agente = new AgenteSoporte("Juan", "juan@test.com");
        DepartamentoAdministracion admin = new DepartamentoAdministracion();
        agente.setSiguiente(admin);
        assertNull(admin.siguiente);
    }
}
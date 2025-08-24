package org.example.ChainOfResponsability;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgenteSoporteTest {
    @Test
    void testCadenaResponsabilidad() {
        AgenteSoporte agente = new AgenteSoporte("Juan", "juan@test.com");
        DepartamentoAdministracion admin = new DepartamentoAdministracion();
        agente.setSiguiente(admin);
        assertTrue(agente.siguiente instanceof DepartamentoAdministracion);
    }
}
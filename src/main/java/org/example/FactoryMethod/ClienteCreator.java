package org.example.FactoryMethod;

public class ClienteCreator extends UserCreator {
    @Override
    public Usuario crearUsuario(String nombre, String correo, String telefono) {
        return new Cliente(nombre, correo, telefono);
    }
}

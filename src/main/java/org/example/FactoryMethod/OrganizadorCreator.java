package org.example.FactoryMethod;

public class OrganizadorCreator extends UserCreator {
    @Override
    public Usuario crearUsuario(String nombre, String correo, String telefono) {
        return new Organizador(nombre, correo, telefono);
    }
}

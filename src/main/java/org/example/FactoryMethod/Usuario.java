package org.example.FactoryMethod;

public abstract class Usuario {
    protected String nombre;
    protected String correo;
    protected String telefono;

    public Usuario(String nombre, String correo, String telefono) {
        if (nombre == null) throw new IllegalArgumentException("El nombre no puede ser null");
        if (correo == null) throw new IllegalArgumentException("El correo no puede ser null");
        if (telefono == null) throw new IllegalArgumentException("El teléfono no puede ser null");
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }
    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}

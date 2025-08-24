package org.example;

public class Ubicacion {
    private String direccion;
    private String ciudad;
    private String pais;
    private double latitud;
    private double longitud;

    public Ubicacion(String direccion, String ciudad, String pais, double latitud, double longitud) {
        this.direccion = cadenaValida(direccion, "dirección");
        this.ciudad = cadenaValida(ciudad, "ciudad");
        this.pais = cadenaValida(pais, "país");
        this.latitud = validarRangoNumerico(latitud, -90, 90, "latitud");
        this.longitud = validarRangoNumerico(longitud, -180, 180, "longitud");
    }

    private String cadenaValida(String data, String nombreCampo) {
        if (data == null || data.trim().isEmpty()) {
            throw new IllegalArgumentException("El " + nombreCampo + " no puede estar vacío.");
        }
        return data;
    }

    private double validarRangoNumerico(double valor, double min, double max, String nombreCampo) {
        if (valor < min || valor > max) {
            throw new IllegalArgumentException("El " + nombreCampo + " debe estar entre " + min + " y " + max + ".");
        }
        return valor;
    }

    // Getters y Setters
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return direccion + ", " + ciudad + ", " + pais +
                " (Lat: " + latitud + ", Lon: " + longitud + ")";
    }
}

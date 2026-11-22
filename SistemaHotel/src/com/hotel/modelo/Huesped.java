package com.hotel.modelo;

public abstract class Huesped {

    private String nombre;
    private String documento;
    private int edad;

    public Huesped(String nombre, String documento, int edad) {
        Validador.validarNombre(nombre);
        Validador.validarDocumento(documento);
        if (edad < 0 || edad > 120) {
            throw new IllegalArgumentException("Edad inválida: " + edad);
        }
        this.nombre = nombre.trim();
        this.documento = documento;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public int getEdad() {
        return edad;
    }

    public abstract String obtenerInfo();
}

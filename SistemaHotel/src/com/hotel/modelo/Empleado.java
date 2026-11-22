package com.hotel.modelo;

public abstract class Empleado {

    private String nombre;
    private String id;
    private String cargo;

    public Empleado(String nombre, String id, String cargo) {
        Validador.validarNombre(nombre);
        Validador.validarTexto(id, "El id");
        Validador.validarTexto(cargo, "El cargo");
        this.nombre = nombre.trim();
        this.id = id.trim();
        this.cargo = cargo.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public String getCargo() {
        return cargo;
    }

    public abstract String generarReporte();
}

package com.hotel.modelo;

public class Camarero extends Empleado {

    private String zona;

    public Camarero(String nombre, String id, String cargo, String zona) {
        super(nombre, id, cargo);
        Validador.validarTexto(zona, "La zona");
        this.zona = zona.trim();
    }

    @Override
    public String generarReporte() {
        return "=== CAMARERO ===\nNombre: " + getNombre() + "\nID: " + getId()
                + "\nCargo: " + getCargo() + "\nZona: " + zona;
    }

    public String getZona() {
        return zona;
    }
}

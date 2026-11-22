package com.hotel.modelo;

public class HuespedRegular extends Huesped {

    private int noches;

    public HuespedRegular(String nombre, String documento, int edad, int noches) {
        super(nombre, documento, edad);
        if (noches <= 0) {
            throw new IllegalArgumentException("Las noches deben ser mayores a 0.");
        }
        if (noches > 365) {
            throw new IllegalArgumentException("Las noches no pueden superar 365.");
        }
        this.noches = noches;
    }

    @Override
    public String obtenerInfo() {
        return "Huésped regular: " + getNombre() + " | Documento: " + getDocumento()
                + " | Edad: " + getEdad() + " | Noches: " + noches;
    }

    public int getNoches() {
        return noches;
    }
}

package com.hotel.modelo;

public final class HuespedVIP extends Huesped {

    private int noches;
    private int descuento;

    public HuespedVIP(String nombre, String documento, int edad, int noches, int descuento) {
        super(nombre, documento, edad);
        if (noches <= 0) {
            throw new IllegalArgumentException("Las noches deben ser mayores a 0.");
        }
        if (noches > 365) {
            throw new IllegalArgumentException("Las noches no pueden superar 365.");
        }
        if (descuento < 0 || descuento > 50) {
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 50.");
        }
        this.noches = noches;
        this.descuento = descuento;
    }

    @Override
    public String obtenerInfo() {
        return "Huésped VIP: " + getNombre() + " | Documento: " + getDocumento()
                + " | Edad: " + getEdad() + " | Noches: " + noches + " | Descuento: " + descuento + "%";
    }

    public int getDescuento() {
        return descuento;
    }

    public int getNoches() {
        return noches;
    }
}

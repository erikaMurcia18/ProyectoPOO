package com.hotel.modelo;

public class Reserva {

    private String fecha;
    private String servicio;
    private Huesped huesped;
    private Recepcionista recepcionista;

    public Reserva(String fecha, String servicio, Huesped huesped, Recepcionista recepcionista) {
        if (huesped == null || recepcionista == null) {
            throw new IllegalArgumentException("La reserva necesita un huésped y un recepcionista.");
        }
        Validador.validarTexto(fecha, "La fecha");
        Validador.validarTexto(servicio, "El servicio");
        this.fecha = fecha;
        this.servicio = servicio;
        this.huesped = huesped;
        this.recepcionista = recepcionista;
    }

    public String getInfo() {
        return "Reserva: " + fecha + " | Servicio: " + servicio
                + " | Recepcionista: " + recepcionista.getNombre()
                + " | Huésped: " + huesped.getNombre();
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public Recepcionista getRecepcionista() {
        return recepcionista;
    }

    public String getFecha() {
        return fecha;
    }
}

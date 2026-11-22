package com.hotel.modelo;

import com.hotel.excepciones.ReservaInvalidaException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;

public class Recepcionista extends Empleado {

    private ArrayList<Reserva> reservas;

    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    public Recepcionista(String nombre, String id, String cargo) {
        super(nombre, id, cargo);
        this.reservas = new ArrayList<>();
    }

    public void hacerReserva(String fecha, Huesped huesped) throws ReservaInvalidaException {
        validarReserva(fecha, "Alojamiento estándar", huesped);
        reservas.add(new Reserva(fecha.trim(), "Alojamiento estándar", huesped, this));
        System.out.println("Reserva hecha para " + fecha.trim() + " a nombre de " + huesped.getNombre());
    }

    public void hacerReserva(String fecha, String servicio, Huesped huesped) throws ReservaInvalidaException {
        validarReserva(fecha, servicio, huesped);
        reservas.add(new Reserva(fecha.trim(), servicio.trim(), huesped, this));
        System.out.println("Reserva hecha para " + fecha.trim() + " con servicio " + servicio.trim()
                + " a nombre de " + huesped.getNombre());
    }

    private void validarReserva(String fecha, String servicio, Huesped huesped) throws ReservaInvalidaException {
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new ReservaInvalidaException("La fecha no puede estar vacía.");
        }
        if (huesped == null) {
            throw new ReservaInvalidaException("Debe indicarse un huésped válido.");
        }
        if (servicio == null || servicio.trim().isEmpty()) {
            throw new ReservaInvalidaException("El servicio no puede estar vacío.");
        }
        if (servicio.trim().length() > 100) {
            throw new ReservaInvalidaException("El servicio no puede superar los 100 caracteres.");
        }
        LocalDate fechaReserva;
        try {
            fechaReserva = LocalDate.parse(fecha.trim(), FORMATO);
        } catch (DateTimeParseException e) {
            throw new ReservaInvalidaException(
                    "Fecha inválida: '" + fecha.trim() + "'. Use el formato dd/MM/aaaa (ejemplo: 25/12/2026).");
        }
        if (fechaReserva.isBefore(LocalDate.now())) {
            throw new ReservaInvalidaException("La fecha de la reserva no puede estar en el pasado.");
        }
        for (Reserva r : reservas) {
            if (r.getHuesped().getDocumento().equals(huesped.getDocumento())
                    && r.getFecha().equals(fecha.trim())) {
                throw new ReservaInvalidaException(
                        "El huésped " + huesped.getNombre() + " ya tiene una reserva para el " + fecha.trim() + ".");
            }
        }
    }

    @Override
    public String generarReporte() {
        return "=== RECEPCIONISTA ===\nNombre: " + getNombre() + "\nID: " + getId()
                + "\nCargo: " + getCargo() + "\nReservas gestionadas: " + reservas.size();
    }

    public ArrayList<Reserva> getReservas() {
        return new ArrayList<>(reservas);
    }
}

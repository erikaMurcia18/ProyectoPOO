package com.hotel.modelo;

import com.hotel.excepciones.HabitacionLlenaException;
import com.hotel.excepciones.HuespedNoEncontradoException;
import com.hotel.excepciones.HuespedYaAlojadoException;

import java.util.ArrayList;

public class Habitacion {

    private String numero;
    private int capacidad;

    private ArrayList<Huesped> huespedes;

    public Habitacion(String numero, int capacidad) {
        Validador.validarNumeroHabitacion(numero);
        if (capacidad <= 0) {
            throw new IllegalArgumentException("Capacidad debe ser mayor a 0.");
        }
        if (capacidad > 20) {
            throw new IllegalArgumentException("La capacidad máxima por habitación es 20.");
        }
        this.numero = numero;
        this.capacidad = capacidad;
        this.huespedes = new ArrayList<>();
    }

    public void alojarHuesped(Huesped h) throws HabitacionLlenaException, HuespedYaAlojadoException {
        if (h == null) {
            throw new IllegalArgumentException("Debe indicarse un huésped válido.");
        }
        if (huespedes.size() >= capacidad) {
            throw new HabitacionLlenaException("Habitación " + numero + " sin cupo disponible.");
        }
        for (Huesped existente : huespedes) {
            if (existente.getDocumento().equals(h.getDocumento())) {
                throw new HuespedYaAlojadoException(
                        "El huésped " + h.getNombre() + " ya está alojado en la habitación " + numero + ".");
            }
        }
        huespedes.add(h);
        System.out.println("Huésped " + h.getNombre() + " alojado en habitación " + numero);
    }

    public void liberarHuesped(String documento) throws HuespedNoEncontradoException {
        Huesped h = buscarHuesped(documento);
        huespedes.remove(h);
        System.out.println("Check-out realizado.");
    }

    public Huesped buscarHuesped(String documento) throws HuespedNoEncontradoException {
        for (Huesped h : huespedes) {
            if (h.getDocumento().equals(documento)) {
                return h;
            }
        }
        throw new HuespedNoEncontradoException(documento);
    }

    public void listarHuespedes() {
        if (huespedes.isEmpty()) {
            System.out.println("No hay huéspedes en esta habitación.");
            return;
        }
        for (Huesped h : huespedes) {
            System.out.println(h.obtenerInfo());
        }
    }

    public String getNumero() {
        return numero;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public ArrayList<Huesped> getHuespedes() {
        return new ArrayList<>(huespedes);
    }
}

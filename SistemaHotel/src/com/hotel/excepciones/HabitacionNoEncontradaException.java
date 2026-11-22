package com.hotel.excepciones;

public class HabitacionNoEncontradaException extends Exception {

    public HabitacionNoEncontradaException(String numero) {
        super("La habitación '" + numero + "' no existe.");
    }
}

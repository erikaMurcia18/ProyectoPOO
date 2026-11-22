package com.hotel.excepciones;

public class HabitacionDuplicadaException extends Exception {

    public HabitacionDuplicadaException(String numero) {
        super("Ya existe una habitación con el número '" + numero + "'.");
    }
}

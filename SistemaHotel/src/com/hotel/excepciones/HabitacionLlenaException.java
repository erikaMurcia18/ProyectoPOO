package com.hotel.excepciones;

public class HabitacionLlenaException extends Exception {

    public HabitacionLlenaException(String mensaje) {
        super(mensaje);
    }
}

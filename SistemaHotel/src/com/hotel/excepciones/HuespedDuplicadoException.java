package com.hotel.excepciones;

public class HuespedDuplicadoException extends Exception {

    public HuespedDuplicadoException(String documento) {
        super("Ya existe un huésped registrado con el documento " + documento + ".");
    }
}

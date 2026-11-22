package com.hotel.excepciones;

public class HuespedNoEncontradoException extends Exception {

    public HuespedNoEncontradoException(String documento) {
        super("Huésped con documento " + documento + " no encontrado.");
    }
}

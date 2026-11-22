package com.hotel.excepciones;

public class EmpleadoNoEncontradoException extends Exception {

    public EmpleadoNoEncontradoException(String id) {
        super("No existe empleado registrado con id " + id + ".");
    }
}

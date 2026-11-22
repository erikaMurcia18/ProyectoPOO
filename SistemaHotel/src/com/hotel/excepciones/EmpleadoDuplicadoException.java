package com.hotel.excepciones;

public class EmpleadoDuplicadoException extends Exception {

    public EmpleadoDuplicadoException(String id) {
        super("Ya existe un empleado registrado con el id " + id + ".");
    }
}

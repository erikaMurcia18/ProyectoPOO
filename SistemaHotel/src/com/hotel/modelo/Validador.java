package com.hotel.modelo;

public final class Validador {

    private Validador() {
    }

    public static void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        for (int i = 0; i < nombre.length(); i++) {
            char c = nombre.charAt(i);
            if (c >= '0' && c <= '9') {
                throw new IllegalArgumentException("El nombre no puede contener números: " + nombre);
            }
        }
        if (!nombre.matches("[\\p{L} .'-]+")) {
            throw new IllegalArgumentException("El nombre solo puede contener letras y espacios: " + nombre);
        }
        if (nombre.trim().length() > 60) {
            throw new IllegalArgumentException("El nombre no puede superar los 60 caracteres.");
        }
    }

    public static void validarDocumento(String documento) {
        if (documento == null || documento.length() < 5 || documento.length() > 12) {
            throw new IllegalArgumentException("El documento debe tener entre 5 y 12 dígitos.");
        }
        for (int i = 0; i < documento.length(); i++) {
            char c = documento.charAt(i);
            if (c < '0' || c > '9') {
                throw new IllegalArgumentException("El documento solo puede contener números.");
            }
        }
    }

    public static void validarTexto(String texto, String campo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede estar vacío.");
        }
    }

    public static void validarNumeroHabitacion(String numero) {
        if (numero == null || !numero.matches("[A-Za-z0-9]{1,10}")) {
            throw new IllegalArgumentException(
                    "El número de habitación debe tener entre 1 y 10 caracteres alfanuméricos.");
        }
    }
}

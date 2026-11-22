package com.hotel.modelo;

import com.hotel.excepciones.EmpleadoDuplicadoException;
import com.hotel.excepciones.EmpleadoNoEncontradoException;
import com.hotel.excepciones.HabitacionDuplicadaException;
import com.hotel.excepciones.HuespedNoEncontradoException;

import java.util.ArrayList;

public class Hotel {

    private String nombre;
    private ArrayList<Habitacion> habitaciones;
    private ArrayList<Empleado> empleados;

    public Hotel(String nombre) {
        Validador.validarTexto(nombre, "El nombre del hotel");
        this.nombre = nombre.trim();
        this.habitaciones = new ArrayList<>();
        this.empleados = new ArrayList<>();
        this.habitaciones.add(new Habitacion("101", 2));
        this.habitaciones.add(new Habitacion("102", 3));
        this.habitaciones.add(new Habitacion("201", 4));
    }

    public void agregarHabitacion(String numero, int capacidad) throws HabitacionDuplicadaException {
        Habitacion nueva = new Habitacion(numero, capacidad);
        if (buscarHabitacion(numero) != null) {
            throw new HabitacionDuplicadaException(numero);
        }
        habitaciones.add(nueva);
        System.out.println("Habitación '" + numero + "' creada con capacidad " + capacidad);
    }

    public void agregarEmpleado(Empleado e) throws EmpleadoDuplicadoException {
        if (e == null) {
            throw new IllegalArgumentException("Debe indicarse un empleado válido.");
        }
        for (Empleado existente : empleados) {
            if (existente.getId().equalsIgnoreCase(e.getId())) {
                throw new EmpleadoDuplicadoException(e.getId());
            }
        }
        empleados.add(e);
        System.out.println(e.getNombre() + " registrado en el hotel.");
    }

    public Habitacion buscarHabitacion(String numero) {
        if (numero == null) {
            return null;
        }
        for (Habitacion h : habitaciones) {
            if (h.getNumero().equalsIgnoreCase(numero.trim())) {
                return h;
            }
        }
        return null;
    }

    public Huesped buscarHuesped(String documento) throws HuespedNoEncontradoException {
        for (Habitacion h : habitaciones) {
            try {
                return h.buscarHuesped(documento);
            } catch (HuespedNoEncontradoException e) {
            }
        }
        throw new HuespedNoEncontradoException(documento);
    }

    public Habitacion buscarHabitacionDeHuesped(String documento) throws HuespedNoEncontradoException {
        for (Habitacion h : habitaciones) {
            try {
                h.buscarHuesped(documento);
                return h;
            } catch (HuespedNoEncontradoException e) {
            }
        }
        throw new HuespedNoEncontradoException(documento);
    }

    public Recepcionista buscarRecepcionista(String id) throws EmpleadoNoEncontradoException {
        for (Empleado e : empleados) {
            if (e instanceof Recepcionista && e.getId().equalsIgnoreCase(id == null ? "" : id.trim())) {
                return (Recepcionista) e;
            }
        }
        throw new EmpleadoNoEncontradoException(id);
    }

    public void listarRecepcionistas() {
        boolean hay = false;
        for (Empleado e : empleados) {
            if (e instanceof Recepcionista) {
                System.out.println(e.getId() + " - " + e.getNombre() + " (" + e.getCargo() + ")");
                hay = true;
            }
        }
        if (!hay) {
            System.out.println("No hay recepcionistas registrados.");
        }
    }

    public void generarReporteGeneral() {
        System.out.println("=== REPORTE GENERAL: " + nombre + " ===");
        for (Habitacion h : habitaciones) {
            System.out.println("Habitación: " + h.getNumero()
                    + " | Ocupación: " + h.getHuespedes().size() + "/" + h.getCapacidad());
        }
        System.out.println("Total de empleados: " + empleados.size());
        for (Empleado e : empleados) {
            System.out.println(e.generarReporte());
            System.out.println("-----------------------");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return new ArrayList<>(habitaciones);
    }

    public ArrayList<Empleado> getEmpleados() {
        return new ArrayList<>(empleados);
    }
}

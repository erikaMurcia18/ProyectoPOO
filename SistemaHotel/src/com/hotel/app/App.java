package com.hotel.app;

import com.hotel.excepciones.EmpleadoDuplicadoException;
import com.hotel.excepciones.EmpleadoNoEncontradoException;
import com.hotel.excepciones.HabitacionDuplicadaException;
import com.hotel.excepciones.HabitacionLlenaException;
import com.hotel.excepciones.HabitacionNoEncontradaException;
import com.hotel.excepciones.HuespedDuplicadoException;
import com.hotel.excepciones.HuespedNoEncontradoException;
import com.hotel.excepciones.HuespedYaAlojadoException;
import com.hotel.excepciones.ReservaInvalidaException;
import com.hotel.modelo.Camarero;
import com.hotel.modelo.Habitacion;
import com.hotel.modelo.Hotel;
import com.hotel.modelo.Huesped;
import com.hotel.modelo.HuespedRegular;
import com.hotel.modelo.HuespedVIP;
import com.hotel.modelo.Recepcionista;
import com.hotel.modelo.Reserva;
import com.hotel.modelo.Validador;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {

    public static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int numero = sc.nextInt();
                sc.nextLine();
                return numero;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Error: debe ingresar un número entero válido.");
            }
        }
    }

    public static String leerTexto(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Huesped> huespedesRegistrados = new ArrayList<>();

        Hotel hotel = new Hotel("Hotel Sabana Real");

        try {
            Recepcionista r1 = new Recepcionista("Camila Prieto", "R001", "Recepción día");
            Recepcionista r2 = new Recepcionista("Andrés Mora", "R002", "Recepción noche");
            Camarero c1 = new Camarero("Luz Marina Díaz", "C001", "Housekeeping", "Piso 1");
            hotel.agregarEmpleado(r1);
            hotel.agregarEmpleado(r2);
            hotel.agregarEmpleado(c1);
        } catch (IllegalArgumentException | EmpleadoDuplicadoException e) {
            System.out.println("Error al crear el personal inicial: " + e.getMessage());
            sc.close();
            return;
        }

        int opcion = -1;
        try {
            do {
                mostrarMenu();
                opcion = leerEntero(sc, "Seleccione una opción: ");

                try {
                    procesarOpcion(opcion, sc, hotel, huespedesRegistrados);

                } catch (HabitacionLlenaException | HuespedNoEncontradoException
                         | EmpleadoNoEncontradoException | ReservaInvalidaException
                         | HabitacionNoEncontradaException | HuespedDuplicadoException
                         | HabitacionDuplicadaException | HuespedYaAlojadoException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Error de validación: " + e.getMessage());
                } catch (NoSuchElementException e) {
                    throw e;
                } catch (RuntimeException e) {
                    System.out.println("Error inesperado: " + e.getMessage());
                }

            } while (opcion != 0);

        } catch (NoSuchElementException e) {
            System.out.println("\nSe cerró la entrada de datos. Finalizando el programa.");
        } finally {
            sc.close();
        }
    }

    private static void procesarOpcion(int opcion, Scanner sc, Hotel hotel, ArrayList<Huesped> registrados)
            throws HabitacionLlenaException, HuespedNoEncontradoException, EmpleadoNoEncontradoException,
            ReservaInvalidaException, HabitacionNoEncontradaException, HuespedDuplicadoException,
            HabitacionDuplicadaException, HuespedYaAlojadoException {

        switch (opcion) {
            case 1:
                registrarHuesped(sc, registrados);
                break;
            case 2:
                listarHuespedesRegistrados(registrados);
                break;
            case 3:
                alojarHuesped(sc, hotel, registrados);
                break;
            case 4:
                hacerReserva(sc, hotel);
                break;
            case 5:
                verHuespedesDeHabitacion(sc, hotel);
                break;
            case 6:
                verReservasDeRecepcionista(sc, hotel);
                break;
            case 7:
                checkOut(sc, hotel);
                break;
            case 8:
                buscarHuesped(sc, hotel);
                break;
            case 9:
                hotel.generarReporteGeneral();
                break;
            case 10:
                crearHabitacion(sc, hotel);
                break;
            case 0:
                System.out.println("\nGracias por visitar Hotel Sabana Real.");
                break;
            default:
                System.out.println("Opción no válida. Elija un número entre 0 y 10.");
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n==================================");
        System.out.println("        HOTEL SABANA REAL");
        System.out.println("==================================");
        System.out.println("1. Registrar huésped");
        System.out.println("2. Listar huéspedes registrados");
        System.out.println("3. Alojar huésped en una habitación");
        System.out.println("4. Hacer una reserva");
        System.out.println("5. Ver huéspedes de una habitación");
        System.out.println("6. Ver reservas de un recepcionista");
        System.out.println("7. Check-out de un huésped");
        System.out.println("8. Buscar huésped por documento");
        System.out.println("9. Reporte general del hotel");
        System.out.println("10. Crear nueva habitación");
        System.out.println("0. Salir");
        System.out.println("==================================");
    }

    private static void registrarHuesped(Scanner sc, ArrayList<Huesped> registrados)
            throws HuespedDuplicadoException {
        System.out.println("\n--- REGISTRO DE HUÉSPED ---");

        String nombre = leerTexto(sc, "Nombre completo: ");
        Validador.validarNombre(nombre);

        String documento = leerTexto(sc, "Número de documento: ");
        Validador.validarDocumento(documento);

        for (Huesped h : registrados) {
            if (h.getDocumento().equals(documento)) {
                throw new HuespedDuplicadoException(documento);
            }
        }

        int edad = leerEntero(sc, "Edad: ");

        System.out.println("1. Regular");
        System.out.println("2. VIP");
        int tipo = leerEntero(sc, "Tipo de huésped: ");

        if (tipo != 1 && tipo != 2) {
            System.out.println("Tipo inválido");
            return;
        }

        int noches = leerEntero(sc, "Número de noches: ");

        Huesped nuevo;
        if (tipo == 1) {
            nuevo = new HuespedRegular(nombre, documento, edad, noches);
        } else {
            int descuento = leerEntero(sc, "Descuento VIP (0 a 50): ");
            nuevo = new HuespedVIP(nombre, documento, edad, noches, descuento);
        }

        registrados.add(nuevo);
        System.out.println("Huésped registrado correctamente.");
        System.out.println(nuevo.obtenerInfo());
    }

    private static void listarHuespedesRegistrados(ArrayList<Huesped> registrados) {
        System.out.println("\n--- HUÉSPEDES REGISTRADOS ---");
        if (registrados.isEmpty()) {
            System.out.println("No hay huéspedes registrados.");
            return;
        }
        for (Huesped h : registrados) {
            System.out.println(h.obtenerInfo());
        }
    }

    private static void alojarHuesped(Scanner sc, Hotel hotel, ArrayList<Huesped> registrados)
            throws HuespedNoEncontradoException, HabitacionNoEncontradaException,
            HabitacionLlenaException, HuespedYaAlojadoException {
        System.out.println("\n--- ALOJAR HUÉSPED ---");

        String documento = leerTexto(sc, "Documento del huésped: ");
        Validador.validarDocumento(documento);

        Huesped huesped = null;
        for (Huesped h : registrados) {
            if (h.getDocumento().equals(documento)) {
                huesped = h;
            }
        }
        if (huesped == null) {
            throw new HuespedNoEncontradoException(documento);
        }

        try {
            Habitacion actual = hotel.buscarHabitacionDeHuesped(documento);
            throw new HuespedYaAlojadoException("El huésped " + huesped.getNombre()
                    + " ya está alojado en la habitación " + actual.getNumero() + ".");
        } catch (HuespedNoEncontradoException e) {
        }

        mostrarHabitaciones(hotel);
        String numero = leerTexto(sc, "Número de habitación: ");
        Habitacion habitacion = obtenerHabitacion(hotel, numero);
        habitacion.alojarHuesped(huesped);
    }

    private static void hacerReserva(Scanner sc, Hotel hotel)
            throws EmpleadoNoEncontradoException, HuespedNoEncontradoException, ReservaInvalidaException {
        System.out.println("\n--- HACER RESERVA ---");

        hotel.listarRecepcionistas();
        String id = leerTexto(sc, "ID del recepcionista: ");

        Recepcionista recepcionista = hotel.buscarRecepcionista(id);

        String documento = leerTexto(sc, "Documento del huésped: ");
        Validador.validarDocumento(documento);
        Huesped huesped = hotel.buscarHuesped(documento);

        String fecha = leerTexto(sc, "Fecha de la reserva (dd/MM/aaaa): ");

        String respuesta;
        do {
            respuesta = leerTexto(sc, "¿Desea agregar un servicio? (s/n): ").toLowerCase();
            if (!respuesta.equals("s") && !respuesta.equals("n")) {
                System.out.println("Responda únicamente con 's' o 'n'.");
            }
        } while (!respuesta.equals("s") && !respuesta.equals("n"));

        if (respuesta.equals("s")) {
            String servicio = leerTexto(sc, "Servicio: ");
            recepcionista.hacerReserva(fecha, servicio, huesped);
        } else {
            recepcionista.hacerReserva(fecha, huesped);
        }
    }

    private static void verHuespedesDeHabitacion(Scanner sc, Hotel hotel)
            throws HabitacionNoEncontradaException {
        System.out.println("\n--- HUÉSPEDES DE UNA HABITACIÓN ---");
        mostrarHabitaciones(hotel);
        String numero = leerTexto(sc, "Número de habitación: ");
        Habitacion habitacion = obtenerHabitacion(hotel, numero);
        habitacion.listarHuespedes();
    }

    private static void verReservasDeRecepcionista(Scanner sc, Hotel hotel)
            throws EmpleadoNoEncontradoException {
        System.out.println("\n--- RESERVAS DE UN RECEPCIONISTA ---");
        hotel.listarRecepcionistas();
        String id = leerTexto(sc, "ID del recepcionista: ");
        Recepcionista recepcionista = hotel.buscarRecepcionista(id);

        ArrayList<Reserva> reservas = recepcionista.getReservas();
        if (reservas.isEmpty()) {
            System.out.println(recepcionista.getNombre() + " no tiene reservas.");
            return;
        }
        for (Reserva r : reservas) {
            System.out.println(r.getInfo());
        }
    }

    private static void checkOut(Scanner sc, Hotel hotel) throws HuespedNoEncontradoException {
        System.out.println("\n--- CHECK-OUT ---");
        String documento = leerTexto(sc, "Documento del huésped: ");
        Validador.validarDocumento(documento);
        Habitacion habitacion = hotel.buscarHabitacionDeHuesped(documento);
        habitacion.liberarHuesped(documento);
    }

    private static void buscarHuesped(Scanner sc, Hotel hotel) throws HuespedNoEncontradoException {
        System.out.println("\n--- BUSCAR HUÉSPED ---");
        String documento = leerTexto(sc, "Documento del huésped: ");
        Validador.validarDocumento(documento);
        Huesped huesped = hotel.buscarHuesped(documento);
        System.out.println(huesped.obtenerInfo());
    }

    private static void crearHabitacion(Scanner sc, Hotel hotel) throws HabitacionDuplicadaException {
        System.out.println("\n--- CREAR HABITACIÓN ---");
        String numero = leerTexto(sc, "Número de habitación: ");
        int capacidad = leerEntero(sc, "Capacidad: ");
        hotel.agregarHabitacion(numero, capacidad);
    }

    private static void mostrarHabitaciones(Hotel hotel) {
        System.out.println("Habitaciones disponibles:");
        for (Habitacion h : hotel.getHabitaciones()) {
            System.out.println("  " + h.getNumero() + " (ocupación " + h.getHuespedes().size()
                    + "/" + h.getCapacidad() + ")");
        }
    }

    private static Habitacion obtenerHabitacion(Hotel hotel, String numero)
            throws HabitacionNoEncontradaException {
        Habitacion habitacion = hotel.buscarHabitacion(numero);
        if (habitacion == null) {
            throw new HabitacionNoEncontradaException(numero);
        }
        return habitacion;
    }
}

# Sistema de Gestión Hotelera — Hotel Sabana Real

Proyecto final de Programación Orientada a Objetos (Java, consola).

## Estructura
```
src/com/hotel/
├── modelo/        Empleado*, Recepcionista, Camarero, Huesped*, HuespedRegular, HuespedVIP (final),
│                  Reserva, Habitacion, Hotel, Validador            (* = abstracta)
├── excepciones/   HabitacionLlena, HuespedNoEncontrado, EmpleadoNoEncontrado, ReservaInvalida
│                  + extras: HabitacionNoEncontrada, HabitacionDuplicada, HuespedDuplicado,
│                    EmpleadoDuplicado, HuespedYaAlojado
└── app/           App (menú principal)
```

## Cómo compilar y ejecutar
Linux / macOS:
```
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out com.hotel.app.App
```
Windows (PowerShell):
```
$files = Get-ChildItem -Recurse src -Filter *.java | ForEach-Object FullName
javac -encoding UTF-8 -d out $files
chcp 65001
java -Dfile.encoding=UTF-8 -cp out com.hotel.app.App
```

## Decisión del equipo (huésped en reservas)
`Hotel.buscarHuesped` solo encuentra huéspedes que ya están alojados en una habitación.
Decisión: para hacer una reserva (opción 4), buscar (8) o hacer check-out (7), el huésped debe estar
registrado (opción 1) y alojado primero (opción 3). Si no lo está, se muestra `HuespedNoEncontradoException`.

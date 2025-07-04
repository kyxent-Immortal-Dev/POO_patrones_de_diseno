import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Clase InterfazConsola que maneja la interacción con el usuario
 * Proporciona un menú interactivo para gestionar reservas del hotel
 */
public class InterfazConsola {
    private Scanner scanner;
    private SistemaReservas sistema;
    private DateTimeFormatter dateFormatter;
    
    public InterfazConsola() {
        this.scanner = new Scanner(System.in);
        this.sistema = SistemaReservas.getInstance();
        this.dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }
    
    public void ejecutar() {
        mostrarBienvenida();
        
        boolean continuar = true;
        while (continuar) {
            mostrarMenuPrincipal();
            int opcion = leerEntero("Seleccione una opción: ");
            
            switch (opcion) {
                case 1: registrarCliente(); break;
                case 2: consultarHabitacionesDisponibles(); break;
                case 3: crearReserva(); break;
                case 4: gestionarServicios(); break;
                case 5: consultarReservas(); break;
                case 6: confirmarReserva(); break;
                case 7: cancelarReserva(); break;
                case 8: mostrarEstadisticas(); break;
                case 9: 
                    System.out.println("\n¡Gracias por usar el sistema de reservas del hotel!");
                    continuar = false;
                    break;
                default:
                    System.out.println("❌ Opción no válida. Por favor, intente nuevamente.");
            }
            
            if (continuar) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }
        }
    }
    
    private void mostrarBienvenida() {
        System.out.println("=".repeat(60));
        System.out.println("🏨 SISTEMA DE RESERVAS DEL HOTEL GRAND PALACE 🏨");
        System.out.println("=".repeat(60));
        System.out.println("Bienvenido al sistema de gestión de reservas");
        System.out.println("=".repeat(60));
    }
    
    private void mostrarMenuPrincipal() {
        System.out.println("\n📋 MENÚ PRINCIPAL");
        System.out.println("-".repeat(30));
        System.out.println("1. 👤 Registrar Cliente");
        System.out.println("2. 🏠 Consultar Habitaciones Disponibles");
        System.out.println("3. 📅 Crear Reserva");
        System.out.println("4. 🛎️ Gestionar Servicios");
        System.out.println("5. 📊 Consultar Reservas");
        System.out.println("6. ✅ Confirmar Reserva");
        System.out.println("7. ❌ Cancelar Reserva");
        System.out.println("8. 📈 Estadísticas");
        System.out.println("9. 🚪 Salir");
        System.out.println("-".repeat(30));
    }
    
    private void registrarCliente() {
        System.out.println("\n👤 REGISTRAR CLIENTE");
        System.out.println("-".repeat(25));
        
        try {
            String nombre = leerTexto("Nombre completo: ");
            String email = leerTexto("Email: ");
            String telefono = leerTexto("Teléfono: ");
            String dni = leerTexto("DNI: ");
            
            Cliente cliente = new Cliente(nombre, email, telefono, dni);
            sistema.registrarCliente(cliente);
            
            System.out.println("✅ Cliente registrado exitosamente!");
            System.out.println("📄 " + cliente);
            
        } catch (ClienteDuplicadoException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error inesperado: " + e.getMessage());
        }
    }
    
    private void consultarHabitacionesDisponibles() {
        System.out.println("\n🏠 HABITACIONES DISPONIBLES");
        System.out.println("-".repeat(35));
        
        List<Habitacion> habitaciones = sistema.obtenerHabitacionesDisponibles();
        
        if (habitaciones.isEmpty()) {
            System.out.println("❌ No hay habitaciones disponibles en este momento.");
            return;
        }
        
        System.out.println("Habitaciones disponibles:");
        for (Habitacion habitacion : habitaciones) {
            System.out.println("🏠 " + habitacion);
        }
        
        System.out.println("\n📊 DISPONIBILIDAD POR TIPO:");
        for (TipoHabitacion tipo : TipoHabitacion.values()) {
            List<Habitacion> habitacionesTipo = sistema.obtenerHabitacionesPorTipo(tipo);
            System.out.println("• " + tipo.getDescripcion() + ": " + habitacionesTipo.size() + " disponibles");
        }
    }
    
    private void crearReserva() {
        System.out.println("\n📅 CREAR RESERVA");
        System.out.println("-".repeat(20));
        
        try {
            String dni = leerTexto("DNI del cliente: ");
            Cliente cliente = sistema.buscarCliente(dni);
            
            if (cliente == null) {
                System.out.println("❌ Cliente no encontrado. ¿Desea registrarlo? (s/n)");
                String respuesta = scanner.nextLine().toLowerCase();
                if (respuesta.equals("s")) {
                    registrarCliente();
                    cliente = sistema.buscarCliente(dni);
                    if (cliente == null) {
                        System.out.println("❌ No se pudo crear la reserva.");
                        return;
                    }
                } else {
                    return;
                }
            }
            
            consultarHabitacionesDisponibles();
            
            int numeroHabitacion = leerEntero("Número de habitación deseada: ");
            Habitacion habitacion = sistema.buscarHabitacion(numeroHabitacion);
            
            if (habitacion == null) {
                System.out.println("❌ Habitación no encontrada.");
                return;
            }
            
            LocalDate fechaEntrada = leerFecha("Fecha de entrada (dd/MM/yyyy): ");
            LocalDate fechaSalida = leerFecha("Fecha de salida (dd/MM/yyyy): ");
            
            if (fechaSalida.isBefore(fechaEntrada) || fechaSalida.isEqual(fechaEntrada)) {
                System.out.println("❌ La fecha de salida debe ser posterior a la fecha de entrada.");
                return;
            }
            
            Reserva reserva = sistema.crearReserva(cliente, habitacion, fechaEntrada, fechaSalida);
            
            System.out.println("✅ Reserva creada exitosamente!");
            System.out.println("📄 " + reserva);
            System.out.println("💰 Total estimado: $" + reserva.calcularTotalConDias());
            
        } catch (HabitacionNoDisponibleException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error inesperado: " + e.getMessage());
        }
    }
    
    private void gestionarServicios() {
        System.out.println("\n🛎️ GESTIONAR SERVICIOS");
        System.out.println("-".repeat(25));
        
        try {
            String idReserva = leerTexto("ID de la reserva: ");
            Reserva reserva = sistema.buscarReserva(idReserva);
            
            if (reserva == null) {
                System.out.println("❌ Reserva no encontrada.");
                return;
            }
            
            System.out.println("📄 Reserva encontrada: " + reserva);
            
            ServicioFactory factory = new ServicioFactory();
            System.out.println("\n🛎️ SERVICIOS DISPONIBLES:");
            for (TipoServicio tipo : TipoServicio.values()) {
                Servicio servicio = factory.crearServicioBasico(tipo);
                System.out.println("• " + servicio);
            }
            
            System.out.println("\nSeleccione el tipo de servicio:");
            System.out.println("1. Desayuno");
            System.out.println("2. WiFi");
            System.out.println("3. Estacionamiento");
            System.out.println("4. Gimnasio");
            
            int opcion = leerEntero("Opción: ");
            TipoServicio tipoSeleccionado = null;
            
            switch (opcion) {
                case 1: tipoSeleccionado = TipoServicio.DESAYUNO; break;
                case 2: tipoSeleccionado = TipoServicio.WIFI; break;
                case 3: tipoSeleccionado = TipoServicio.ESTACIONAMIENTO; break;
                case 4: tipoSeleccionado = TipoServicio.GIMNASIO; break;
                default:
                    System.out.println("❌ Opción no válida.");
                    return;
            }
            
            System.out.println("\n¿Desea agregar servicios adicionales?");
            boolean incluirSpa = leerBooleano("¿Incluir Spa? (+$80) (s/n): ");
            boolean incluirRestaurante = leerBooleano("¿Incluir Restaurante? (+$50) (s/n): ");
            
            Servicio servicio = factory.crearServicioCompleto(tipoSeleccionado, incluirSpa, incluirRestaurante);
            reserva.agregarServicio(servicio);
            
            System.out.println("✅ Servicio agregado exitosamente!");
            System.out.println("🛎️ " + servicio);
            System.out.println("💰 Nuevo total de la reserva: $" + reserva.calcularTotalConDias());
            
        } catch (Exception e) {
            System.out.println("❌ Error inesperado: " + e.getMessage());
        }
    }
    
    private void consultarReservas() {
        System.out.println("\n📊 CONSULTAR RESERVAS");
        System.out.println("-".repeat(25));
        
        System.out.println("1. Todas las reservas");
        System.out.println("2. Reservas por cliente");
        
        int opcion = leerEntero("Seleccione una opción: ");
        
        switch (opcion) {
            case 1:
                List<Reserva> todasReservas = sistema.obtenerReservas();
                if (todasReservas.isEmpty()) {
                    System.out.println("❌ No hay reservas registradas.");
                } else {
                    System.out.println("\n📋 TODAS LAS RESERVAS:");
                    for (Reserva reserva : todasReservas) {
                        System.out.println("📄 " + reserva);
                    }
                }
                break;
                
            case 2:
                String dni = leerTexto("DNI del cliente: ");
                List<Reserva> reservasCliente = sistema.obtenerReservasCliente(dni);
                if (reservasCliente.isEmpty()) {
                    System.out.println("❌ No hay reservas para este cliente.");
                } else {
                    System.out.println("\n📋 RESERVAS DEL CLIENTE:");
                    for (Reserva reserva : reservasCliente) {
                        System.out.println("📄 " + reserva);
                    }
                }
                break;
                
            default:
                System.out.println("❌ Opción no válida.");
        }
    }
    
    private void confirmarReserva() {
        System.out.println("\n✅ CONFIRMAR RESERVA");
        System.out.println("-".repeat(25));
        
        try {
            String idReserva = leerTexto("ID de la reserva: ");
            Reserva reserva = sistema.buscarReserva(idReserva);
            
            if (reserva == null) {
                System.out.println("❌ Reserva no encontrada.");
                return;
            }
            
            System.out.println("📄 " + reserva);
            
            if (reserva.getEstado() == EstadoReserva.CONFIRMADA) {
                System.out.println("❌ Esta reserva ya está confirmada.");
                return;
            }
            
            if (reserva.getEstado() == EstadoReserva.CANCELADA) {
                System.out.println("❌ Esta reserva está cancelada.");
                return;
            }
            
            boolean confirmar = leerBooleano("¿Confirmar esta reserva? (s/n): ");
            if (confirmar) {
                reserva.confirmarReserva();
                System.out.println("✅ Reserva confirmada exitosamente!");
            } else {
                System.out.println("❌ Operación cancelada.");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error inesperado: " + e.getMessage());
        }
    }
    
    private void cancelarReserva() {
        System.out.println("\n❌ CANCELAR RESERVA");
        System.out.println("-".repeat(25));
        
        try {
            String idReserva = leerTexto("ID de la reserva: ");
            Reserva reserva = sistema.buscarReserva(idReserva);
            
            if (reserva == null) {
                System.out.println("❌ Reserva no encontrada.");
                return;
            }
            
            System.out.println("📄 " + reserva);
            
            if (reserva.getEstado() == EstadoReserva.CANCELADA) {
                System.out.println("❌ Esta reserva ya está cancelada.");
                return;
            }
            
            boolean cancelar = leerBooleano("¿Está seguro de cancelar esta reserva? (s/n): ");
            if (cancelar) {
                reserva.cancelarReserva();
                System.out.println("✅ Reserva cancelada exitosamente!");
            } else {
                System.out.println("❌ Operación cancelada.");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error inesperado: " + e.getMessage());
        }
    }
    
    private void mostrarEstadisticas() {
        System.out.println("\n📈 ESTADÍSTICAS DEL HOTEL");
        System.out.println("-".repeat(35));
        
        System.out.println("🏠 Habitaciones disponibles: " + sistema.contarHabitacionesDisponibles());
        System.out.println("📅 Reservas activas: " + sistema.contarReservasActivas());
        System.out.println("👤 Clientes registrados: " + sistema.obtenerClientes().size());
        System.out.println("📊 Total de reservas: " + sistema.obtenerReservas().size());
        System.out.println("💰 Ingresos totales: $" + String.format("%.2f", sistema.calcularIngresosTotales()));
    }
    
    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }
    
    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("❌ Por favor, ingrese un número válido.");
            }
        }
    }
    
    private boolean leerBooleano(String mensaje) {
        while (true) {
            String respuesta = leerTexto(mensaje).toLowerCase();
            if (respuesta.equals("s") || respuesta.equals("si")) {
                return true;
            } else if (respuesta.equals("n") || respuesta.equals("no")) {
                return false;
            } else {
                System.out.println("❌ Por favor, responda 's' o 'n'.");
            }
        }
    }
    
    private LocalDate leerFecha(String mensaje) {
        while (true) {
            try {
                String fechaStr = leerTexto(mensaje);
                return LocalDate.parse(fechaStr, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("❌ Formato de fecha inválido. Use dd/MM/yyyy (ejemplo: 15/12/2024).");
            }
        }
    }
} 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase SistemaReservas que implementa el patrón Singleton
 * Gestiona todas las operaciones del sistema de reservas del hotel
 */
public class SistemaReservas {
    private static SistemaReservas instancia;
    private List<Cliente> clientes;
    private List<Habitacion> habitaciones;
    private List<Reserva> reservas;
    private Map<String, Cliente> clientesPorDni;
    private Map<Integer, Habitacion> habitacionesPorNumero;
    private int contadorReservas;
    
    // Constructor privado para implementar Singleton
    private SistemaReservas() {
        this.clientes = new ArrayList<>();
        this.habitaciones = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.clientesPorDni = new HashMap<>();
        this.habitacionesPorNumero = new HashMap<>();
        this.contadorReservas = 1;
        inicializarHabitaciones();
    }
    
    // Método estático para obtener la instancia única
    public static SistemaReservas getInstance() {
        if (instancia == null) {
            instancia = new SistemaReservas();
        }
        return instancia;
    }
    
    // Inicializar habitaciones del hotel
    private void inicializarHabitaciones() {
        // Crear habitaciones usando Factory
        HabitacionFactory factory = new HabitacionFactory();
        
        // Habitaciones simples
        for (int i = 101; i <= 110; i++) {
            Habitacion habitacion = factory.crearHabitacion(i, TipoHabitacion.SIMPLE);
            habitaciones.add(habitacion);
            habitacionesPorNumero.put(i, habitacion);
        }
        
        // Habitaciones dobles
        for (int i = 201; i <= 210; i++) {
            Habitacion habitacion = factory.crearHabitacion(i, TipoHabitacion.DOBLE);
            habitaciones.add(habitacion);
            habitacionesPorNumero.put(i, habitacion);
        }
        
        // Suites
        for (int i = 301; i <= 305; i++) {
            Habitacion habitacion = factory.crearHabitacion(i, TipoHabitacion.SUITE);
            habitaciones.add(habitacion);
            habitacionesPorNumero.put(i, habitacion);
        }
        
        // Suite presidencial
        Habitacion habitacion = factory.crearHabitacion(401, TipoHabitacion.PRESIDENCIAL);
        habitaciones.add(habitacion);
        habitacionesPorNumero.put(401, habitacion);
    }
    
    // Métodos de gestión de clientes
    public void registrarCliente(Cliente cliente) throws ClienteDuplicadoException {
        if (clientesPorDni.containsKey(cliente.getDni())) {
            throw new ClienteDuplicadoException("Cliente con DNI " + cliente.getDni() + " ya existe");
        }
        clientes.add(cliente);
        clientesPorDni.put(cliente.getDni(), cliente);
    }
    
    public Cliente buscarCliente(String dni) {
        return clientesPorDni.get(dni);
    }
    
    public List<Cliente> obtenerClientes() {
        return new ArrayList<>(clientes);
    }
    
    // Métodos de gestión de habitaciones
    public List<Habitacion> obtenerHabitacionesDisponibles() {
        List<Habitacion> disponibles = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.isDisponible()) {
                disponibles.add(habitacion);
            }
        }
        return disponibles;
    }
    
    public List<Habitacion> obtenerHabitacionesPorTipo(TipoHabitacion tipo) {
        List<Habitacion> habitacionesTipo = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getTipo() == tipo && habitacion.isDisponible()) {
                habitacionesTipo.add(habitacion);
            }
        }
        return habitacionesTipo;
    }
    
    public Habitacion buscarHabitacion(int numero) {
        return habitacionesPorNumero.get(numero);
    }
    
    // Métodos de gestión de reservas
    public Reserva crearReserva(Cliente cliente, Habitacion habitacion, 
                               LocalDate fechaEntrada, LocalDate fechaSalida) 
                               throws HabitacionNoDisponibleException {
        if (!habitacion.isDisponible()) {
            throw new HabitacionNoDisponibleException("Habitación " + habitacion.getNumero() + " no disponible");
        }
        
        String id = "RES-" + String.format("%04d", contadorReservas++);
        Reserva reserva = new Reserva(id, cliente, habitacion, fechaEntrada, fechaSalida);
        reservas.add(reserva);
        
        return reserva;
    }
    
    public List<Reserva> obtenerReservasCliente(String dni) {
        List<Reserva> reservasCliente = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva.getCliente().getDni().equals(dni)) {
                reservasCliente.add(reserva);
            }
        }
        return reservasCliente;
    }
    
    public List<Reserva> obtenerReservas() {
        return new ArrayList<>(reservas);
    }
    
    public Reserva buscarReserva(String id) {
        for (Reserva reserva : reservas) {
            if (reserva.getId().equals(id)) {
                return reserva;
            }
        }
        return null;
    }
    
    // Métodos de estadísticas
    public int contarHabitacionesDisponibles() {
        return obtenerHabitacionesDisponibles().size();
    }
    
    public int contarReservasActivas() {
        int count = 0;
        for (Reserva reserva : reservas) {
            if (reserva.getEstado() == EstadoReserva.CONFIRMADA) {
                count++;
            }
        }
        return count;
    }
    
    public double calcularIngresosTotales() {
        double total = 0;
        for (Reserva reserva : reservas) {
            if (reserva.getEstado() == EstadoReserva.CONFIRMADA || 
                reserva.getEstado() == EstadoReserva.COMPLETADA) {
                total += reserva.calcularTotalConDias();
            }
        }
        return total;
    }
} 
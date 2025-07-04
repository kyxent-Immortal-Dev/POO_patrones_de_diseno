import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Reserva que representa una reserva en el hotel
 * Implementa el patrón Subject para notificar cambios a los observadores
 */
public class Reserva implements Subject {
    private String id;
    private Cliente cliente;
    private Habitacion habitacion;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private List<Servicio> servicios;
    private List<Observer> observers;
    private EstadoReserva estado;
    
    public Reserva(String id, Cliente cliente, Habitacion habitacion, 
                   LocalDate fechaEntrada, LocalDate fechaSalida) {
        this.id = id;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.servicios = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.estado = EstadoReserva.PENDIENTE;
        
        // Agregar el cliente como observador
        addObserver(cliente);
    }
    
    // Implementación del patrón Subject
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notifyObservers(String mensaje) {
        for (Observer observer : observers) {
            observer.update(mensaje);
        }
    }
    
    // Métodos de negocio
    public void agregarServicio(Servicio servicio) {
        servicios.add(servicio);
        notifyObservers("Servicio agregado: " + servicio.getNombre());
    }
    
    public void confirmarReserva() {
        this.estado = EstadoReserva.CONFIRMADA;
        habitacion.setDisponible(false);
        notifyObservers("Reserva confirmada para las fechas " + fechaEntrada + " al " + fechaSalida);
    }
    
    public void cancelarReserva() {
        this.estado = EstadoReserva.CANCELADA;
        habitacion.setDisponible(true);
        notifyObservers("Reserva cancelada");
    }
    
    public double calcularTotal() {
        double total = habitacion.getPrecio();
        for (Servicio servicio : servicios) {
            total += servicio.getPrecio();
        }
        return total;
    }
    
    public int getDias() {
        return (int) (fechaSalida.toEpochDay() - fechaEntrada.toEpochDay());
    }
    
    public double calcularTotalConDias() {
        return calcularTotal() * getDias();
    }
    
    // Getters y setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Habitacion getHabitacion() {
        return habitacion;
    }
    
    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }
    
    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }
    
    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }
    
    public LocalDate getFechaSalida() {
        return fechaSalida;
    }
    
    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    
    public List<Servicio> getServicios() {
        return new ArrayList<>(servicios);
    }
    
    public EstadoReserva getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return "Reserva{" +
                "id='" + id + '\'' +
                ", cliente=" + cliente.getNombre() +
                ", habitacion=" + habitacion.getNumero() +
                ", fechaEntrada=" + fechaEntrada +
                ", fechaSalida=" + fechaSalida +
                ", estado=" + estado +
                ", total=$" + calcularTotalConDias() +
                '}';
    }
} 
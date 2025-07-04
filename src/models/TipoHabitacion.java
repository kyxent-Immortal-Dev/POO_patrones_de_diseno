/**
 * Enum para definir los tipos de habitaciones disponibles
 */
public enum TipoHabitacion {
    SIMPLE(100.0, "Habitación Simple"),
    DOBLE(150.0, "Habitación Doble"),
    SUITE(300.0, "Suite"),
    PRESIDENCIAL(500.0, "Suite Presidencial");
    
    private final double precio;
    private final String descripcion;
    
    TipoHabitacion(double precio, String descripcion) {
        this.precio = precio;
        this.descripcion = descripcion;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
} 
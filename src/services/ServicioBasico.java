/**
 * Clase ServicioBasico que implementa servicios básicos del hotel
 * Forma parte del patrón Decorator
 */
public class ServicioBasico extends Servicio {
    
    public ServicioBasico(String nombre, double precio, String descripcion) {
        super(nombre, precio, descripcion);
    }
    
    @Override
    public double getPrecio() {
        return precio;
    }
    
    @Override
    public String getDescripcion() {
        return descripcion;
    }
} 
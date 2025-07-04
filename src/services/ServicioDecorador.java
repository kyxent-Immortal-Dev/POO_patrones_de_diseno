/**
 * Clase ServicioDecorador que implementa el patrón Decorator
 * Permite agregar servicios adicionales a un servicio base
 */
public abstract class ServicioDecorador extends Servicio {
    protected Servicio servicio;
    
    public ServicioDecorador(Servicio servicio, String nombre, double precio, String descripcion) {
        super(nombre, precio, descripcion);
        this.servicio = servicio;
    }
    
    @Override
    public double getPrecio() {
        return servicio.getPrecio() + precio;
    }
    
    @Override
    public String getDescripcion() {
        return servicio.getDescripcion() + " + " + descripcion;
    }
} 
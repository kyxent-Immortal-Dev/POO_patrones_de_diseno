/**
 * Clase abstracta Servicio que define la base para todos los servicios del hotel
 * Implementa el patrón Decorator para agregar servicios adicionales
 */
public abstract class Servicio {
    protected String nombre;
    protected double precio;
    protected String descripcion;
    
    public Servicio(String nombre, double precio, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }
    
    // Métodos abstractos para ser implementados por las clases concretas
    public abstract double getPrecio();
    public abstract String getDescripcion();
    
    // Getters y setters
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return nombre + " - $" + getPrecio() + " (" + getDescripcion() + ")";
    }
} 
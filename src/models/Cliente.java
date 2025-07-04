import java.util.ArrayList;
import java.util.List;

/**
 * Clase Cliente que representa un cliente del hotel
 * Implementa el patrón Observer para recibir notificaciones
 */
public class Cliente implements Observer {
    private String nombre;
    private String email;
    private String telefono;
    private String dni;
    private List<String> notificaciones;
    
    public Cliente(String nombre, String email, String telefono, String dni) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.dni = dni;
        this.notificaciones = new ArrayList<>();
    }
    
    // Getters y setters con encapsulamiento
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getDni() {
        return dni;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public List<String> getNotificaciones() {
        return new ArrayList<>(notificaciones);
    }
    
    // Implementación del patrón Observer
    @Override
    public void update(String mensaje) {
        notificaciones.add(mensaje);
        System.out.println("📧 Notificación para " + nombre + ": " + mensaje);
    }
    
    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente cliente = (Cliente) obj;
        return dni.equals(cliente.dni);
    }
    
    @Override
    public int hashCode() {
        return dni.hashCode();
    }
} 
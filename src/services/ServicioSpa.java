/**
 * Clase ServicioSpa que implementa un decorador concreto para servicios de spa
 */
public class ServicioSpa extends ServicioDecorador {
    
    public ServicioSpa(Servicio servicio) {
        super(servicio, "Spa", 80.0, "Acceso completo al spa y relajación");
    }
} 
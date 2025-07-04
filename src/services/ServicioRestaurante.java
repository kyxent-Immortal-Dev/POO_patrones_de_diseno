/**
 * Clase ServicioRestaurante que implementa un decorador concreto para servicios de restaurante
 */
public class ServicioRestaurante extends ServicioDecorador {
    
    public ServicioRestaurante(Servicio servicio) {
        super(servicio, "Restaurante", 50.0, "Comida gourmet incluida");
    }
} 
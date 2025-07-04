/**
 * Clase ServicioFactory que implementa el patrón Factory para servicios
 * Se encarga de crear servicios básicos y decorados
 */
public class ServicioFactory {
    
    /**
     * Crear servicio básico según el tipo
     */
    public Servicio crearServicioBasico(TipoServicio tipo) {
        switch (tipo) {
            case DESAYUNO:
                return new ServicioBasico("Desayuno", 25.0, "Desayuno buffet completo");
            case WIFI:
                return new ServicioBasico("WiFi", 10.0, "Internet de alta velocidad");
            case ESTACIONAMIENTO:
                return new ServicioBasico("Estacionamiento", 15.0, "Estacionamiento privado");
            case GIMNASIO:
                return new ServicioBasico("Gimnasio", 20.0, "Acceso al gimnasio 24 horas");
            default:
                throw new IllegalArgumentException("Tipo de servicio no válido: " + tipo);
        }
    }
    
    /**
     * Crear servicio decorado con servicios adicionales
     */
    public Servicio crearServicioCompleto(TipoServicio tipoBase, boolean incluirSpa, boolean incluirRestaurante) {
        Servicio servicio = crearServicioBasico(tipoBase);
        
        if (incluirSpa) {
            servicio = new ServicioSpa(servicio);
        }
        
        if (incluirRestaurante) {
            servicio = new ServicioRestaurante(servicio);
        }
        
        return servicio;
    }
} 
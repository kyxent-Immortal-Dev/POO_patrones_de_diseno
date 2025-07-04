/**
 * Clase HabitacionFactory que implementa el patrón Factory
 * Se encarga de crear habitaciones según el tipo especificado
 */
public class HabitacionFactory {
    
    /**
     * Método factory para crear habitaciones según el tipo
     */
    public Habitacion crearHabitacion(int numero, TipoHabitacion tipo) {
        switch (tipo) {
            case SIMPLE:
                return new Habitacion(numero, tipo, "Habitación simple con cama individual", 1);
            case DOBLE:
                return new Habitacion(numero, tipo, "Habitación doble con cama matrimonial", 2);
            case SUITE:
                return new Habitacion(numero, tipo, "Suite con sala de estar separada", 4);
            case PRESIDENCIAL:
                return new Habitacion(numero, tipo, "Suite presidencial con todas las comodidades", 6);
            default:
                throw new IllegalArgumentException("Tipo de habitación no válido: " + tipo);
        }
    }
} 
/**
 * Excepción para cuando una habitación no está disponible
 */
public class HabitacionNoDisponibleException extends HotelException {
    public HabitacionNoDisponibleException(String mensaje) {
        super(mensaje);
    }
} 
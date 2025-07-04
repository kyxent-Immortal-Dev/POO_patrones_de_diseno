/**
 * Excepción personalizada base para el sistema de reservas del hotel
 */
public class HotelException extends Exception {
    public HotelException(String mensaje) {
        super(mensaje);
    }
    
    public HotelException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
} 
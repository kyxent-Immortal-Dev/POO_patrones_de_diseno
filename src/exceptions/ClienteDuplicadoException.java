/**
 * Excepción para cuando se intenta registrar un cliente duplicado
 */
public class ClienteDuplicadoException extends HotelException {
    public ClienteDuplicadoException(String mensaje) {
        super(mensaje);
    }
} 
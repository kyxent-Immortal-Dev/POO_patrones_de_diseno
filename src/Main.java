/**
 * Clase Main - Punto de entrada de la aplicación
 * Sistema de Reservas de Hotel - Implementación académica
 * 
 * Esta aplicación implementa los siguientes patrones de diseño:
 * - Singleton: SistemaReservas (instancia única del sistema)
 * - Factory: HabitacionFactory y ServicioFactory (creación de objetos)
 * - Decorator: ServicioDecorador (servicios adicionales)
 * - Observer: Cliente recibe notificaciones de cambios en reservas
 * 
 * Principios de POO aplicados:
 * - Encapsulamiento: Atributos privados con getters/setters
 * - Herencia: Jerarquía de servicios y excepciones
 * - Polimorfismo: Métodos abstractos y interfaces
 * - Abstracción: Interfaces Observer/Subject y clase abstracta Servicio
 * 
 * Manejo de excepciones personalizado:
 * - HotelException (base)
 * - ClienteDuplicadoException
 * - HabitacionNoDisponibleException
 * 
 * @author Sistema de Reservas Hotel
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        // Mensaje de bienvenida con información del proyecto
        System.out.println("=".repeat(80));
        System.out.println("🏨 SISTEMA DE RESERVAS DE HOTEL - PROYECTO ACADÉMICO 🏨");
        System.out.println("=".repeat(80));
        System.out.println("📚 Implementa conceptos avanzados de POO y patrones de diseño:");
        System.out.println("   • Patrón Singleton: SistemaReservas");
        System.out.println("   • Patrón Factory: HabitacionFactory y ServicioFactory");
        System.out.println("   • Patrón Decorator: ServicioDecorador");
        System.out.println("   • Patrón Observer: Notificaciones a clientes");
        System.out.println("   • Manejo de excepciones personalizado");
        System.out.println("   • Principios de encapsulamiento, herencia y polimorfismo");
        System.out.println("=".repeat(80));
        
        try {
            // Crear e inicializar la interfaz de consola
            InterfazConsola interfaz = new InterfazConsola();
            
            // Ejecutar la aplicación
            interfaz.ejecutar();
            
        } catch (Exception e) {
            System.err.println("❌ Error crítico en la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 
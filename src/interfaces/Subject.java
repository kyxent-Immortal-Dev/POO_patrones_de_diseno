/**
 * Interfaz Subject para el patrón Observer
 * Los objetos que implementen esta interfaz podrán notificar a sus observadores
 */
public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String mensaje);
} 
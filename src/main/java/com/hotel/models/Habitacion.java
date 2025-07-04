package com.hotel.models;

/**
 * Clase Habitación que representa una habitación del hotel
 * Implementa principios de encapsulamiento y herencia
 */
public class Habitacion {
    private int numero;
    private TipoHabitacion tipo;
    private boolean disponible;
    private String descripcion;
    private int capacidad;
    
    public Habitacion(int numero, TipoHabitacion tipo, String descripcion, int capacidad) {
        this.numero = numero;
        this.tipo = tipo;
        this.disponible = true;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
    }
    
    // Getters y setters
    public int getNumero() {
        return numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public TipoHabitacion getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoHabitacion tipo) {
        this.tipo = tipo;
    }
    
    public boolean isDisponible() {
        return disponible;
    }
    
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public int getCapacidad() {
        return capacidad;
    }
    
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    public double getPrecio() {
        return tipo.getPrecio();
    }
    
    @Override
    public String toString() {
        return "Habitación " + numero + " - " + tipo.getDescripcion() + 
               " (Capacidad: " + capacidad + ", Precio: $" + getPrecio() + 
               ", Disponible: " + (disponible ? "Sí" : "No") + ")";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Habitacion habitacion = (Habitacion) obj;
        return numero == habitacion.numero;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(numero);
    }
} 
package com.hotel.models;

/**
 * Enum para definir los tipos de servicios básicos disponibles
 */
public enum TipoServicio {
    DESAYUNO("Desayuno"),
    WIFI("WiFi"),
    ESTACIONAMIENTO("Estacionamiento"),
    GIMNASIO("Gimnasio");
    
    private final String descripcion;
    
    TipoServicio(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    @Override
    public String toString() {
        return descripcion;
    }
} 
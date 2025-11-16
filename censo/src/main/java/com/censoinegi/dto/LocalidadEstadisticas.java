package com.censoinegi.dto;

public class LocalidadEstadisticas {
    private String nombreLocalidad;
    private String nombreMunicipio;
    private Long totalHabitantes;
    private Long totalViviendas;

    public LocalidadEstadisticas(String nombreLocalidad, String nombreMunicipio, Long totalHabitantes, Long totalViviendas) {
        this.nombreLocalidad = nombreLocalidad;
        this.nombreMunicipio = nombreMunicipio;
        this.totalHabitantes = totalHabitantes;
        this.totalViviendas = totalViviendas;
    }

    // Getters y Setters
    public String getNombreLocalidad() {
        return nombreLocalidad;
    }

    public void setNombreLocalidad(String nombreLocalidad) {
        this.nombreLocalidad = nombreLocalidad;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public Long getTotalHabitantes() {
        return totalHabitantes;
    }

    public void setTotalHabitantes(Long totalHabitantes) {
        this.totalHabitantes = totalHabitantes;
    }

    public Long getTotalViviendas() {
        return totalViviendas;
    }

    public void setTotalViviendas(Long totalViviendas) {
        this.totalViviendas = totalViviendas;
    }
}
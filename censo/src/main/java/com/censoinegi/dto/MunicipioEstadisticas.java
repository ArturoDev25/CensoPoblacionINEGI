package com.censoinegi.dto;

public class MunicipioEstadisticas {
    private String nombreMunicipio;
    private Long totalHabitantes;
    private Long totalViviendas;
    private Long totalLocalidades;

    public MunicipioEstadisticas(String nombreMunicipio, Long totalHabitantes, Long totalViviendas, Long totalLocalidades) {
        this.nombreMunicipio = nombreMunicipio;
        this.totalHabitantes = totalHabitantes;
        this.totalViviendas = totalViviendas;
        this.totalLocalidades = totalLocalidades;
    }

    // Getters y Setters
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

    public Long getTotalLocalidades() {
        return totalLocalidades;
    }

    public void setTotalLocalidades(Long totalLocalidades) {
        this.totalLocalidades = totalLocalidades;
    }
}
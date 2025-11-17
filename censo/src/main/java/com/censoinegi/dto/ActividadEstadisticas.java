package com.censoinegi.dto;

public class ActividadEstadisticas {
    private String nombreActividad;
    private Long totalHabitantes;

    public ActividadEstadisticas(String nombreActividad, Long totalHabitantes) {
        this.nombreActividad = nombreActividad;
        this.totalHabitantes = totalHabitantes;
    }

    // Getters y Setters
    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public Long getTotalHabitantes() {
        return totalHabitantes;
    }

    public void setTotalHabitantes(Long totalHabitantes) {
        this.totalHabitantes = totalHabitantes;
    }
}
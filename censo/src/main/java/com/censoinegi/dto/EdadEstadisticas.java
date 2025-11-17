package com.censoinegi.dto;

public class EdadEstadisticas {
    private String rangoEdad;
    private Long totalHabitantes;

    public EdadEstadisticas(String rangoEdad, Long totalHabitantes) {
        this.rangoEdad = rangoEdad;
        this.totalHabitantes = totalHabitantes;
    }

    // Getters y Setters
    public String getRangoEdad() {
        return rangoEdad;
    }

    public void setRangoEdad(String rangoEdad) {
        this.rangoEdad = rangoEdad;
    }

    public Long getTotalHabitantes() {
        return totalHabitantes;
    }

    public void setTotalHabitantes(Long totalHabitantes) {
        this.totalHabitantes = totalHabitantes;
    }
}
package com.censoinegi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.censoinegi.dto.ActividadEstadisticas;
import com.censoinegi.model.Habitante;

@Repository
public interface HabitanteRepository extends JpaRepository<Habitante, Integer> {
    
    // Consulta para obtener estadísticas por actividad económica
    @Query("""
        SELECT new com.censoinegi.dto.ActividadEstadisticas(
            COALESCE(a.nombre, 'Sin actividad'),
            COUNT(h.id)
        )
        FROM Habitante h
        LEFT JOIN h.actividadEconomica a
        GROUP BY a.id, a.nombre
        ORDER BY COUNT(h.id) DESC
    """)
    List<ActividadEstadisticas> obtenerEstadisticasPorActividad();
}
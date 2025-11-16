package com.censoinegi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.censoinegi.dto.LocalidadEstadisticas;
import com.censoinegi.model.Localidad;

@Repository
public interface LocalidadRepository extends JpaRepository<Localidad, Integer> {

    List<Localidad> findByMunicipioId(Integer municipioId);

    // Consulta para obtener estadísticas de población por localidad
    @Query("""
        SELECT new com.censoinegi.dto.LocalidadEstadisticas(
            l.nombre,
            m.nombre,
            COUNT(DISTINCT h.id),
            COUNT(DISTINCT v.id)
        )
        FROM Localidad l
        LEFT JOIN l.municipio m
        LEFT JOIN l.viviendas v
        LEFT JOIN v.habitantes h
        GROUP BY l.id, l.nombre, m.nombre
        ORDER BY COUNT(DISTINCT h.id) DESC
    """)
    List<LocalidadEstadisticas> obtenerEstadisticasPorLocalidad();
}
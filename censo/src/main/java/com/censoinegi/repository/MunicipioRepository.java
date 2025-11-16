package com.censoinegi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.censoinegi.dto.MunicipioEstadisticas;
import com.censoinegi.model.Municipio;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

    // Consulta para obtener estadísticas de población por municipio
    @Query("""
        SELECT new com.censoinegi.dto.MunicipioEstadisticas(
            m.nombre,
            COUNT(DISTINCT h.id),
            COUNT(DISTINCT v.id),
            COUNT(DISTINCT l.id)
        )
        FROM Municipio m
        LEFT JOIN m.viviendas v
        LEFT JOIN v.habitantes h
        LEFT JOIN m.localidades l
        GROUP BY m.id, m.nombre
        ORDER BY COUNT(DISTINCT h.id) DESC
    """)
    List<MunicipioEstadisticas> obtenerEstadisticasPorMunicipio();
}
package com.censoinegi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.censoinegi.dto.ActividadEstadisticas;
import com.censoinegi.dto.EdadEstadisticas;
import com.censoinegi.model.Habitante;
import com.censoinegi.repository.HabitanteRepository;

@Service
public class HabitanteService {

    @Autowired
    private HabitanteRepository habitanteRepository;

    public List<Habitante> findAll() {
        return habitanteRepository.findAll();
    }

    public Optional<Habitante> findById(Integer id) {

        return habitanteRepository.findById(id);
    }

    public Habitante save(Habitante habitante) {
        return habitanteRepository.save(habitante);
    }

    public void deleteById(Integer id) {
        
        habitanteRepository.deleteById(id);
    }

    public long contarHabitantes() {
        return habitanteRepository.count();
    }

    // Nuevo método para estadísticas por actividad
    public List<ActividadEstadisticas> obtenerEstadisticasPorActividad() {
        return habitanteRepository.obtenerEstadisticasPorActividad();
    }

    // Nuevo método para estadísticas por rango de edad --M
    public List<EdadEstadisticas> obtenerEstadisticasPorEdad() {
        List<Habitante> habitantes = habitanteRepository.findAll();
        
        // Contadores por rango de edad
        Map<String, Long> rangosMap = new HashMap<>();
        rangosMap.put("0-17 años", 0L);
        rangosMap.put("18-29 años", 0L);
        rangosMap.put("30-44 años", 0L);
        rangosMap.put("45-59 años", 0L);
        rangosMap.put("60+ años", 0L);
        
        // Clasificar habitantes por edad
        for (Habitante h : habitantes) {
            Integer edad = h.getEdad();
            if (edad == null) continue;
            
            if (edad < 18) {
                rangosMap.put("0-17 años", rangosMap.get("0-17 años") + 1);
            } else if (edad < 30) {
                rangosMap.put("18-29 años", rangosMap.get("18-29 años") + 1);
            } else if (edad < 45) {
                rangosMap.put("30-44 años", rangosMap.get("30-44 años") + 1);
            } else if (edad < 60) {
                rangosMap.put("45-59 años", rangosMap.get("45-59 años") + 1);
            } else {
                rangosMap.put("60+ años", rangosMap.get("60+ años") + 1);
            }
        }
        
        // Convertir a lista de DTOs
        return rangosMap.entrySet().stream()
            .map(entry -> new EdadEstadisticas(entry.getKey(), entry.getValue()))
            .toList();
    }
}

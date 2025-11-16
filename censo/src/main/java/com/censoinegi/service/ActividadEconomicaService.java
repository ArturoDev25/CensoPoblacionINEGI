package com.censoinegi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.censoinegi.model.ActividadEconomica;
import com.censoinegi.repository.ActividadEconomicaRepository;

@Service
public class ActividadEconomicaService {

    @Autowired
    private ActividadEconomicaRepository actividadEconomicaRepository;

    public List<ActividadEconomica> findAll() {
        return actividadEconomicaRepository.findAll();
    }

    public Optional<ActividadEconomica> findById(Integer id) {
        return actividadEconomicaRepository.findById(id);
    }

    public ActividadEconomica save(ActividadEconomica actividad) {
        return actividadEconomicaRepository.save(actividad);
    }

    public void deleteById(Integer id) {
        actividadEconomicaRepository.deleteById(id);
    }

    public long contarActividades() {
        return actividadEconomicaRepository.count();
    }
}

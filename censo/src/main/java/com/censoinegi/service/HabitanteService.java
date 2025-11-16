package com.censoinegi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        // CAMBIADO DE String a Integer
        return habitanteRepository.findById(id);
    }

    public Habitante save(Habitante habitante) {
        return habitanteRepository.save(habitante);
    }

    public void deleteById(Integer id) {
        // CAMBIADO DE String a Integer
        habitanteRepository.deleteById(id);
    }

    public long contarHabitantes() {
        return habitanteRepository.count();
    }
}


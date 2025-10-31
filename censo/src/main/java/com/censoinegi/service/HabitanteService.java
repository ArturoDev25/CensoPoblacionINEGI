package com.censoinegi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Optional<Habitante> findById(UUID id) {
        return habitanteRepository.findById(id);
    }

    public Habitante save(Habitante habitante) {
        return habitanteRepository.save(habitante);
    }

    public void deleteById(UUID id) {
        habitanteRepository.deleteById(id);
    }
}

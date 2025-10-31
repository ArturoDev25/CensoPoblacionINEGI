package com.censoinegi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.censoinegi.model.Localidad;
import com.censoinegi.repository.LocalidadRepository;

@Service
public class LocalidadService {

    @Autowired
    private LocalidadRepository localidadRepository;

    public List<Localidad> findAll() {
        return localidadRepository.findAll();
    }

    public Optional<Localidad> findById(UUID id) {
        return localidadRepository.findById(id);
    }

    public Localidad save(Localidad localidad) {
        return localidadRepository.save(localidad);
    }

    public void deleteById(UUID id) {
        localidadRepository.deleteById(id);
    }
}

package com.censoinegi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.censoinegi.model.Municipio;
import com.censoinegi.repository.MunicipioRepository;

@Service
public class MunicipioService {

    @Autowired
    private MunicipioRepository municipioRepository;

    public List<Municipio> findAll() {
        return municipioRepository.findAll();
    }

    public Optional<Municipio> findById(UUID id) {
        return municipioRepository.findById(id);
    }

    public Municipio save(Municipio municipio) {
        return municipioRepository.save(municipio);
    }

    public void deleteById(UUID id) {
        municipioRepository.deleteById(id);
    }
// Nuevo metodo--metodo para contar municipios M
        public long contarMunicipios() {
    return municipioRepository.count();
    }
}

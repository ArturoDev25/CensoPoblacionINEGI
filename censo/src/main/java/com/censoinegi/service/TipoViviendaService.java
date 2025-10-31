package com.censoinegi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.censoinegi.model.TipoVivienda;
import com.censoinegi.repository.TipoViviendaRepository;

@Service
public class TipoViviendaService {

    @Autowired
    private TipoViviendaRepository tipoViviendaRepository;

    public List<TipoVivienda> findAll() {
        return tipoViviendaRepository.findAll();
    }

    public Optional<TipoVivienda> findById(Integer id) {
        return tipoViviendaRepository.findById(id);
    }

    public TipoVivienda save(TipoVivienda tipoVivienda) {
        return tipoViviendaRepository.save(tipoVivienda);
    }

    public void deleteById(Integer id) {
        tipoViviendaRepository.deleteById(id);
    }
}

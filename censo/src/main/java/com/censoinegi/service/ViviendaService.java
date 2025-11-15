package com.censoinegi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.censoinegi.model.Vivienda;
import com.censoinegi.repository.ViviendaRepository;

@Service
public class ViviendaService {

    @Autowired
    private ViviendaRepository viviendaRepository;

    public List<Vivienda> findAll() {
        return viviendaRepository.findAll();
    }

    public Optional<Vivienda> findById(Integer id) {
        return viviendaRepository.findById(id);
    }

    public Vivienda save(Vivienda vivienda) {
        return viviendaRepository.save(vivienda);
    }

    public void deleteById(Integer id) {
        viviendaRepository.deleteById(id);
    }
         // Nuevo metodo--metodo para contar viviendas M 
    public long contarViviendas() {
        return viviendaRepository.count();
    }
}

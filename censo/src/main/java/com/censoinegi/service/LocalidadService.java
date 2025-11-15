package com.censoinegi.service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Localidad> findById(Integer id) {
        return localidadRepository.findById(id);
    }

    public Localidad save(Localidad localidad) {
        return localidadRepository.save(localidad);
    }

    public void deleteById(Integer id) {
        localidadRepository.deleteById(id);
    }

    // Método para el select dinámico
    public List<Localidad> findByMunicipioId(Integer municipioId) {
        return localidadRepository.findByMunicipioId(municipioId);

    }
}

    // Nuevo metodo--metodo para contar
    //public long contarLocalidades() {
    //return LocalidadRepository.count();
   // }
//}
    

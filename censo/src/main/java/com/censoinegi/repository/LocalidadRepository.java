package com.censoinegi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.censoinegi.model.Localidad;

@Repository
public interface LocalidadRepository extends JpaRepository<Localidad, UUID> {
    
    // Spring Data JPA crea la query automáticamente
    List<Localidad> findByMunicipioId(UUID municipioId);
}

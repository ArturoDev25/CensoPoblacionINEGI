package com.censoinegi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.censoinegi.model.Localidad;

@Repository
public interface LocalidadRepository extends JpaRepository<Localidad, Integer> {

    List<Localidad> findByMunicipioId(Integer municipioId);

}

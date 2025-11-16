package com.censoinegi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.censoinegi.model.ActividadEconomica;

@Repository
public interface ActividadEconomicaRepository extends JpaRepository<ActividadEconomica, Integer> {

}

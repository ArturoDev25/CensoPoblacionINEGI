package com.censoinegi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.censoinegi.model.TipoVivienda;

@Repository
public interface TipoViviendaRepository extends JpaRepository<TipoVivienda, Integer> {

}

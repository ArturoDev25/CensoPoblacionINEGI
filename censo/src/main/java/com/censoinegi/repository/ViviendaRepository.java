package com.censoinegi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.censoinegi.model.Vivienda;

@Repository
public interface ViviendaRepository extends JpaRepository<Vivienda, UUID> {

}

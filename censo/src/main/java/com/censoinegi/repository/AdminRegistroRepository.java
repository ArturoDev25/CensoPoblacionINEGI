package com.censoinegi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.censoinegi.model.AdminRegistro;

public interface AdminRegistroRepository extends JpaRepository<AdminRegistro, UUID> {

}

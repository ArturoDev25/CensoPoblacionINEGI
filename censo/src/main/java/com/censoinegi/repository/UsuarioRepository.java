package com.censoinegi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.censoinegi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Usuario findByCorreo(String correo);
}

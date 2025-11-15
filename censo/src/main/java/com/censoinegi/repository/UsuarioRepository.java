package com.censoinegi.repository;



import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.censoinegi.model.Usuario;
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Usuario findByCorreo(String correo);
}

package com.censoinegi.repository;



import org.springframework.data.jpa.repository.JpaRepository;



import com.censoinegi.model.Usuario;
import java.util.UUID;
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Usuario findByCorreo(String correo);
}

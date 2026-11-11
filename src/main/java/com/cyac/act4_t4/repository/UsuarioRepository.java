package com.cyac.act4_t4.repository;

import com.cyac.act4_t4.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Spring Data JPA crea la consulta SQL automáticamente por el nombre del método
    Optional<Usuario> findByUsername(String username);
}
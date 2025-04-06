package com.usuarios.repositorio;

import com.usuarios.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositorioUsuario extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreo(String correo);
}

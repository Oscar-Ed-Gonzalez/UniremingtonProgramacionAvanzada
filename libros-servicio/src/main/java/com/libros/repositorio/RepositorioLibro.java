package com.libros.repositorio;

import com.libros.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioLibro extends JpaRepository<Libro,Integer> {
}

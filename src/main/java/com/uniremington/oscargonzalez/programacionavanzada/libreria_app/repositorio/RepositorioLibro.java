package com.uniremington.oscargonzalez.programacionavanzada.libreria_app.repositorio;

import com.uniremington.oscargonzalez.programacionavanzada.libreria_app.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioLibro extends JpaRepository<Libro,Integer> {
}

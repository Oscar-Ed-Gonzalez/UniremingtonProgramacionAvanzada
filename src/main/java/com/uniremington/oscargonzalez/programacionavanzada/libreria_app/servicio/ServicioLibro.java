package com.uniremington.oscargonzalez.programacionavanzada.libreria_app.servicio;

import com.uniremington.oscargonzalez.programacionavanzada.libreria_app.modelo.Libro;
import com.uniremington.oscargonzalez.programacionavanzada.libreria_app.repositorio.RepositorioLibro;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioLibro {

    private final RepositorioLibro repositorioLibro;

    public ServicioLibro(RepositorioLibro repositorioLibro){
        this.repositorioLibro = repositorioLibro;
    }

    public List<Libro> obtenerLibros(){
        return repositorioLibro.findAll();
    }

    public Optional<Libro> obtenerLibrosPorId(int id){
        return repositorioLibro.findById(id);
    }

    public Libro crearOactualizarLibro(Libro libro) {
        return repositorioLibro.save(libro);
    }

    public void eliminarLibro(int id) {
        repositorioLibro.deleteById(id);
    }
}

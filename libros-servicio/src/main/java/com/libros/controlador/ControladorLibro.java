package com.libros.controlador;

import com.libros.modelo.Libro;
import com.libros.servicio.ServicioLibro;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libros")
public class ControladorLibro {
    private final ServicioLibro servicioLibro;

    public ControladorLibro(ServicioLibro servicioLibro) {
        this.servicioLibro = servicioLibro;
    }

    @GetMapping
    public List<Libro> obtenerLibros(){
        return servicioLibro.obtenerLibros();
    }

    @GetMapping("/{id}")
    public Optional<Libro> obtenerLibrosPorId(@PathVariable int id){
        return servicioLibro.obtenerLibrosPorId(id);
    }

    @PostMapping
    public Libro crearLibro(@RequestBody Libro libro) {
        return servicioLibro.crearOactualizarLibro(libro);
    }

    @PutMapping("/{id}")
    public Libro actualizarLibro(@PathVariable int id, @RequestBody Libro libro) {
        libro.setId(id);
        return servicioLibro.crearOactualizarLibro(libro);
    }

    @DeleteMapping("/{id}")
    public void eliminarLibro(@PathVariable int id) {
        servicioLibro.eliminarLibro(id);
    }
}


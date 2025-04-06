package com.libros;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ServicioLibroTest {

    @Mock
    private RepositorioLibro repositorioLibro;

    @InjectMocks
    private ServicioLibro servicioLibro;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obtenerLibrosTest() {

        Libro libro1 = new Libro("Título 1", "Autor 1", 10.0);
        Libro libro2 = new Libro("Título 2", "Autor 2", 15.0);
        when(repositorioLibro.findAll()).thenReturn(Arrays.asList(libro1, libro2));

        List<Libro> result = servicioLibro.obtenerLibros();

        assertEquals(2, result.size());
        verify(repositorioLibro, times(1)).findAll();
    }

    @Test
    void crearOactualizarlibroTest() {

        //Dado
        Libro libro = new Libro("Título 3", "Autor 3", 20.0);
        when(repositorioLibro.save(libro)).thenReturn(libro);

        //Cuando
        Libro libroGuardado = servicioLibro.crearOactualizarLibro(libro);

        //Entonces
        assertNotNull(libroGuardado);
        assertEquals("Título 3", libroGuardado.getTitulo());

        // Verificamos que se llamó al repositorio (mock)
        verify(repositorioLibro, times(1)).save(libro);
    }

    @Test
    void obtenerLibrosPorIdTest(){

        Libro libro = new Libro("Título 4", "Autor 4", 100.99);
        libro.setId(1);
        when(repositorioLibro.findById(1)).thenReturn(Optional.of(libro));

        Optional <Libro> libroOptional = servicioLibro.obtenerLibrosPorId(1);
        Libro libroObtenido = libroOptional.orElse(null);

        assertNotNull(libroObtenido);
        assertEquals("Título 4", libroObtenido.getTitulo());

        verify(repositorioLibro, times(1)).findById(1);
    }

}


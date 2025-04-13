package com.libros;

import com.libros.modelo.Libro;
import com.libros.repositorio.RepositorioLibro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ControladorLibroTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RepositorioLibro repositorioLibro;

    @Test
    void crearLibroTest() throws Exception {
        // Prepara el JSON a enviar
        String jsonLibro = "{\"titulo\":\"Libro de prueba\",\"autor\":\"autor de prueba\",\"precio\":80000}";

        // Ejecuta la petición POST y valida la respuesta
        mockMvc.perform(post("/api/libros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonLibro))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.titulo").value("Libro de prueba"))
                .andExpect(jsonPath("$.autor").value("autor de prueba"))
                .andExpect(jsonPath("$.precio").value(80000));
    }

    @Test
    void obtenerLibrosTest() throws Exception {
        // Limpia la base de datos y agrega datos de prueba
        repositorioLibro.deleteAll();
        repositorioLibro.save(new Libro("Libro 1", "Autor 1", 10.0));
        repositorioLibro.save(new Libro("Libro 2", "Autor 2", 20.0));

        // Ejecuta la petición GET y valida la respuesta
        mockMvc.perform(get("/api/libros"))
                .andExpect(status().isOk())
                // Se asume que el endpoint retorna un array JSON,
                // se utiliza $.length() para comprobar la cantidad de elementos
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void eliminarLibroTest() throws Exception {
        // Limpia la base de datos y agrega dos libros de prueba
        repositorioLibro.deleteAll();
        Libro libro1 = repositorioLibro.save(new Libro("Libro 1", "Autor 1", 10.0));
        repositorioLibro.save(new Libro("Libro 2", "Autor 2", 20.0));

        // Ejecuta la petición DELETE para eliminar el primer libro
        mockMvc.perform(delete("/api/libros/{id}", libro1.getId()))
                .andExpect(status().isOk());

        // Verifica que solo queda un libro en la base de datos
        mockMvc.perform(get("/api/libros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }
}

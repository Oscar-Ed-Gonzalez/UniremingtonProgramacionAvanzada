package com.uniremington.oscargonzalez.programacionavanzada.libreria_app;

import com.uniremington.oscargonzalez.programacionavanzada.libreria_app.modelo.Libro;
import com.uniremington.oscargonzalez.programacionavanzada.libreria_app.repositorio.RepositorioLibro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
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
        // Limpia la DB en memoria y agrega datos de prueba
        repositorioLibro.deleteAll();
        repositorioLibro.save(new Libro("Libro 1", "Autor 1", 10.0));
        repositorioLibro.save(new Libro("Libro 2", "Autor 2", 20.0));

        mockMvc.perform(get("/api/libros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}

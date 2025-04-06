package com.libros;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class ControladorLibroTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private RepositorioLibro repositorioLibro;

    @Test
    void crearLibroTest() throws Exception {
        // Prepara el JSON a enviar
        String jsonLibro = "{\"titulo\":\"Libro de prueba\",\"autor\":\"autor de prueba\",\"precio\":80000}";

        // Ejecuta la petición POST y valida la respuesta
        webTestClient.post()
                .uri("/api/libros")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(jsonLibro)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").exists()
                .jsonPath("$.titulo").isEqualTo("Libro de prueba")
                .jsonPath("$.autor").isEqualTo("autor de prueba")
                .jsonPath("$.precio").isEqualTo(80000);
    }

    @Test
    void obtenerLibrosTest() throws Exception {
        // Limpia la DB en memoria y agrega datos de prueba
        repositorioLibro.deleteAll();
        repositorioLibro.save(new Libro("Libro 1", "Autor 1", 10.0));
        repositorioLibro.save(new Libro("Libro 2", "Autor 2", 20.0));

        webTestClient.get()
                .uri("/api/libros")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Libro.class)
                .hasSize(2);
    }

    @Test
    void eliminarLibroTest() throws Exception {
        // Limpia la DB y agrega dos libros de prueba
        repositorioLibro.deleteAll();
        Libro libro1 = repositorioLibro.save(new Libro("Libro 1", "Autor 1", 10.0));
        repositorioLibro.save(new Libro("Libro 2", "Autor 2", 20.0));

        // Ejecuta la petición DELETE para eliminar el primer libro
        webTestClient.delete()
                .uri("/api/libros/{id}", libro1.getId())
                .exchange()
                .expectStatus().isOk();

        // Verifica que solo queda un libro en la base de datos
        webTestClient.get()
                .uri("/api/libros")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Libro.class)
                .hasSize(1);
    }

}

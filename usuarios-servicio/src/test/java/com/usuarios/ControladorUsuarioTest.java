package com.usuarios;

import com.usuarios.modelo.Usuario;
import com.usuarios.repositorio.RepositorioUsuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.usuarios.seguridad.JwtUtil;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class ControladorUsuarioTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void ObtenerTodosTest(){

        // Limpia la base de datos para garantizar un entorno controlado
        repositorioUsuario.deleteAll();

        // Crea y guarda dos usuarios de prueba
        Usuario usuario1 = new Usuario();
        usuario1.setNombre("Juan");
        usuario1.setCorreo("juan@example.com");
        usuario1.setContrasena("1234");
        usuario1.setRol("USER");

        Usuario usuario2 = new Usuario();
        usuario2.setNombre("Martha");
        usuario2.setCorreo("martha@example.com");
        usuario2.setContrasena("abcd");
        usuario2.setRol("ADMIN");

        repositorioUsuario.save(usuario1);
        repositorioUsuario.save(usuario2);

        // Generar un token válido para autenticación, usando uno de los usuarios (por ejemplo, Juan)
        String token = jwtUtil.generarToken("juan@example.com", "USER");

        // Realizar la llamada GET al endpoint "/api/usuarios" con el header de autorización
        webTestClient.get()
                .uri("/api/usuarios")
                .header("Authorization", "Bearer " + token)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Usuario.class)
                .hasSize(2)
                .consumeWith(response -> {
                    assertThat(response.getResponseBody()).isNotNull();
                });
    }

}

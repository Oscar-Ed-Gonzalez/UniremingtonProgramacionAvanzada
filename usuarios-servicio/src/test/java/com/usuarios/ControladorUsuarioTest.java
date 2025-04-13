package com.usuarios;

import com.usuarios.modelo.Usuario;
import com.usuarios.repositorio.RepositorioUsuario;
import com.usuarios.seguridad.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ControladorUsuarioTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void obtenerTodosTest() throws Exception {

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
        mockMvc.perform(get("/api/usuarios")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

}

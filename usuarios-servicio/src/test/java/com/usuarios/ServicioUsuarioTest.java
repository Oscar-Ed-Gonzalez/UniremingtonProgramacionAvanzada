package com.usuarios;

import com.usuarios.modelo.Usuario;
import com.usuarios.repositorio.RepositorioUsuario;
import com.usuarios.servicio.ServicioUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ServicioUsuarioTest {

    @Mock
    private RepositorioUsuario  repositorioUsuario;

    @InjectMocks
    private ServicioUsuario servicioUsuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loginTest(){

        // Datos de prueba
        String correo = "usuario@example.com";
        String contrasenaCorrecta = "1234";
        String contrasenaIncorrecta = "abcd";

        // Dado
        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasenaCorrecta);

        when(repositorioUsuario.findByCorreo(correo)).thenReturn(Optional.of(usuario));

        // 1. Cuando
        Optional<Usuario> resultado = servicioUsuario.login(correo, contrasenaCorrecta);
        // 1. Entonces
        assertTrue(resultado.isPresent(), "El login debería ser exitoso con contraseña correcta");
        assertEquals(usuario, resultado.get(), "El usuario devuelto debe ser el esperado");

        // 2. Cuando
        Optional<Usuario> resultadoIncorrecto = servicioUsuario.login(correo, contrasenaIncorrecta);
        // 2. Entonces
        assertFalse(resultadoIncorrecto.isPresent(), "El login debería fallar con contraseña incorrecta");
    }

    @Test
    void obtenerTodosTest() {
        // 1. Datos de prueba
        Usuario u1 = new Usuario();
        u1.setCorreo("user1@example.com");
        u1.setContrasena("pass1");
        Usuario u2 = new Usuario();
        u2.setCorreo("user2@example.com");
        u2.setContrasena("pass2");
        // Dado
        List<Usuario> listaEsperada = List.of(u1, u2);

        when(repositorioUsuario.findAll()).thenReturn(listaEsperada);

        // Cuando
        List<Usuario> resultado = servicioUsuario.obtenerTodos();

        // Entonces
        assertNotNull(resultado, "La lista de usuarios no debe ser nula");
        assertEquals(listaEsperada.size(), resultado.size(), "Debe retornar la cantidad correcta de usuarios");
        assertEquals(listaEsperada, resultado, "La lista devuelta debe coincidir con la esperada");
    }
}


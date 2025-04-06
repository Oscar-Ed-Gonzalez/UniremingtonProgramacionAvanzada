package com.usuarios;

import com.usuarios.modelo.Usuario;
import com.usuarios.repositorio.RepositorioUsuario;
import com.usuarios.servicio.ServicioUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
}


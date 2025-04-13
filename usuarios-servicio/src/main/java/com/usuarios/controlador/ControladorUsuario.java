package com.usuarios.controlador;

import com.usuarios.modelo.Usuario;
import com.usuarios.seguridad.JwtUtil;
import com.usuarios.servicio.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class ControladorUsuario {

    @Autowired
    private ServicioUsuario servicioUsuario;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String correo = body.get("correo");
        String contrasena = body.get("contrasena");
        Optional<Usuario> usuario = servicioUsuario.login(correo, contrasena);

        if (usuario.isPresent()) {
            String token = jwtUtil.generarToken(usuario.get().getCorreo(), usuario.get().getRol());
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping
    public List<Usuario> obtenerTodos() {
        return servicioUsuario.obtenerTodos();
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario u) {
        return servicioUsuario.guardarUsuario(u);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable int id) {
        servicioUsuario.eliminarUsuario(id);
    }
}

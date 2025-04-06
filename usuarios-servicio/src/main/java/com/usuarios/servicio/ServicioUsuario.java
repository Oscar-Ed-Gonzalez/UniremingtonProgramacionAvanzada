package com.usuarios.servicio;

import com.usuarios.modelo.Usuario;
import com.usuarios.repositorio.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioUsuario {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    public List<Usuario> obtenerTodos() {
        return repositorioUsuario.findAll();
    }

    public Optional<Usuario> obtenerPorId(int id) {
        return repositorioUsuario.findById(id);
    }

    public Usuario guardarUsuario(Usuario u) {
        return repositorioUsuario.save(u);
    }

    public void eliminarUsuario(int id) {
        repositorioUsuario.deleteById(id);
    }

    public Optional<Usuario> login(String correo, String contraseña) {
        return repositorioUsuario.findByCorreo(correo)
                .filter(u -> u.getContraseña().equals(contraseña));
    }
}

package com.pedidos.servicio;

import com.pedidos.cliente.ClienteLibro;
import com.pedidos.cliente.ClienteUsuario;
import com.pedidos.dto.LibroDTO;
import com.pedidos.dto.UsuarioDTO;
import com.pedidos.excepciones.ResourceNotFoundException;
import com.pedidos.modelo.Pedido;
import com.pedidos.repositorio.RepositorioPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServicioPedido {

    @Autowired
    private RepositorioPedido repositorioPedido;

    @Autowired
    private ClienteUsuario clienteUsuario;

    @Autowired
    private ClienteLibro clienteLibro;

    public Pedido registrarPedido(Integer idUsuario, Integer idLibro) {
        // Obtener y validar la existencia del usuario
        UsuarioDTO usuario = clienteUsuario.obtenerUsuario(idUsuario);
        if (usuario == null) {
            throw new ResourceNotFoundException("Usuario con id " + idUsuario + " no encontrado.");
        }

        // Obtener y validar la existencia del libro
        LibroDTO libro = clienteLibro.obtenerLibro(idLibro);
        if (libro == null) {
            throw new ResourceNotFoundException("Libro con id " + idLibro + " no encontrado.");
        }

        // Crear el pedido
        Pedido pedido = new Pedido();
        pedido.setIdUsuario(usuario.getId());
        pedido.setIdLibro(libro.getId());
        pedido.setFecha(LocalDateTime.now());
        // Opcional: almacenar más datos para referencia (por ejemplo, nombre del usuario o título del libro)
        // pedido.setNombreUsuario(usuario.getNombre());
        // pedido.setTituloLibro(libro.getTitulo());

        return repositorioPedido.save(pedido);
    }

    public List<Pedido> obtenerTodos() {
        return repositorioPedido.findAll();
    }
}

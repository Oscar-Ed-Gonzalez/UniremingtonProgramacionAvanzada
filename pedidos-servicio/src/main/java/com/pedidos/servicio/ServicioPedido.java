package com.pedidos.servicio;

import com.pedidos.cliente.ClienteLibro;
import com.pedidos.cliente.ClienteUsuario;
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
        // Validar existencia
        Object usuario = clienteUsuario.obtenerUsuario(idUsuario);
        Object libro = clienteLibro.obtenerLibro(idLibro);

        Pedido pedido = new Pedido();
        pedido.setIdUsuario(idUsuario);
        pedido.setIdLibro(idLibro);
        pedido.setFecha(LocalDateTime.now());
        return repositorioPedido.save(pedido);
    }

    public List<Pedido> obtenerTodos() {
        return repositorioPedido.findAll();
    }
}

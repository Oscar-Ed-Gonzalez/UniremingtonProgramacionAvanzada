package com.pedidos.controlador;

import com.pedidos.modelo.Pedido;
import com.pedidos.servicio.ServicioPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
public class ControladorPedido {

    @Autowired
    private ServicioPedido servicioPedido;

    @PostMapping
    public Pedido crearPedido(@RequestBody Map<String, Integer> body) {
        Integer idUsuario = body.get("idUsuario");
        Integer idLibro = body.get("idLibro");
        return servicioPedido.registrarPedido(idUsuario, idLibro);
    }

    @GetMapping
    public List<Pedido> listar() {
        return servicioPedido.obtenerTodos();
    }
}

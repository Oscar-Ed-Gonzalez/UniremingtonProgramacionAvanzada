package com.pedidos.repositorio;

import com.pedidos.modelo.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioPedido extends JpaRepository<Pedido, Long> {

}

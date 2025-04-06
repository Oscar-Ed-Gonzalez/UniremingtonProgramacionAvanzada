package com.pedidos.cliente;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuarios-servicio")
public interface ClienteUsuario {

    @GetMapping("/api/usuarios/{id}")
    Object obtenerUsuario(@PathVariable("id") Integer id);
}

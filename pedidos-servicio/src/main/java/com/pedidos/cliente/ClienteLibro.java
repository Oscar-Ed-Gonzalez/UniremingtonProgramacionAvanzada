package com.pedidos.cliente;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "libros-servicio")
public interface ClienteLibro {

    @GetMapping("/api/libros/{id}")
    Object obtenerLibro(@PathVariable("id") Integer id);
}

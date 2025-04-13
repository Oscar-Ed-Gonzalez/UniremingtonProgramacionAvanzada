package com.pedidos.cliente;

import com.pedidos.dto.LibroDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "libros-servicio")
public interface ClienteLibro {

    @GetMapping("/api/libros/{id}")
    LibroDTO obtenerLibro(@PathVariable("id") Integer id);
}

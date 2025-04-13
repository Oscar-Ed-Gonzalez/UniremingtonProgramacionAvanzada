package com.pedidos.cliente;

import com.pedidos.dto.UsuarioDTO;
import com.pedidos.config.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuarios-servicio", configuration = FeignClientConfiguration.class)
public interface ClienteUsuario {

    @GetMapping("/api/usuarios/{id}")
    UsuarioDTO obtenerUsuario(@PathVariable("id") Integer id);
}

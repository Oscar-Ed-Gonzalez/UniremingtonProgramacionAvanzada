package com.pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.pedidos.cliente")
public class PedidosServicioApplication {

    public static void main(String[] args) {
        SpringApplication.run(PedidosServicioApplication.class, args);
    }
}

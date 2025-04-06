package com.pedidos;

import com.pedidos.modelo.Pedido;
import com.pedidos.repositorio.RepositorioPedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        properties = {"spring.main.web-application-type=servlet"}
)
@AutoConfigureMockMvc
public class ControladorPedidoTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RepositorioPedido repositorioPedido;

    @BeforeEach
    void setUp() {
        // Limpiar la base y agregar dos pedidos de ejemplo
        repositorioPedido.deleteAll();
        Pedido p1 = new Pedido();
        p1.setIdUsuario(1);
        p1.setIdLibro(10);
        p1.setFecha(LocalDateTime.now());

        Pedido p2 = new Pedido();
        p2.setIdUsuario(2);
        p2.setIdLibro(20);
        p2.setFecha(LocalDateTime.now());

        repositorioPedido.save(p1);
        repositorioPedido.save(p2);
    }

    @Test
    void listarPedidosTest() throws Exception {
        mockMvc.perform(get("/api/pedidos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // Se espera una lista de 2 pedidos
                .andExpect(jsonPath("$", hasSize(2)))
                // Verifica el primer pedido
                .andExpect(jsonPath("$[0].idUsuario", is(1)))
                .andExpect(jsonPath("$[0].idLibro", is(10)))
                // Verifica el segundo pedido
                .andExpect(jsonPath("$[1].idUsuario", is(2)))
                .andExpect(jsonPath("$[1].idLibro", is(20)));
    }
}

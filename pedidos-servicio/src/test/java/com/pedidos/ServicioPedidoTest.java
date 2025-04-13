package com.pedidos;

import com.pedidos.cliente.ClienteLibro;
import com.pedidos.cliente.ClienteUsuario;
import com.pedidos.dto.LibroDTO;
import com.pedidos.dto.UsuarioDTO;
import com.pedidos.modelo.Pedido;
import com.pedidos.repositorio.RepositorioPedido;
import com.pedidos.servicio.ServicioPedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ServicioPedidoTest {

    @Mock
    private RepositorioPedido repositorioPedido;

    @Mock
    private ClienteUsuario clienteUsuario;

    @Mock
    private ClienteLibro clienteLibro;

    @InjectMocks
    private ServicioPedido servicioPedido;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrarPedidoTest() {

        Integer idUsuario = 1;
        Integer idLibro = 10;

        // Dado: crear DTOs con los valores esperados
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(idUsuario);
        usuarioDTO.setNombre("Usuario de Prueba");
        // Asigna otros atributos si es necesario

        LibroDTO libroDTO = new LibroDTO();
        libroDTO.setId(idLibro);
        libroDTO.setTitulo("Libro de Prueba");
        // Asigna otros atributos si es necesario

        // Configurar los mocks para que retornen los DTOs con datos correctos
        when(clienteUsuario.obtenerUsuario(idUsuario)).thenReturn(usuarioDTO);
        when(clienteLibro.obtenerLibro(idLibro)).thenReturn(libroDTO);

        // Configurar el repositorio para que retorne el pedido tal como se guarda
        when(repositorioPedido.save(any(Pedido.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Cuando
        Pedido resultado = servicioPedido.registrarPedido(idUsuario, idLibro);

        // Entonces
        assertNotNull(resultado);
        assertEquals(idUsuario, resultado.getIdUsuario());
        assertEquals(idLibro, resultado.getIdLibro());
        assertNotNull(resultado.getFecha());

        // Verificar que se llamaron los Feign Clients
        verify(clienteUsuario, times(1)).obtenerUsuario(idUsuario);
        verify(clienteLibro, times(1)).obtenerLibro(idLibro);
        verify(repositorioPedido, times(1)).save(any(Pedido.class));
    }
}

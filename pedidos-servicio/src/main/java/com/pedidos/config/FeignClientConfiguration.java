package com.pedidos.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Configuration
public class FeignClientConfiguration {

    @Value("${usuarios.servicio.login.url}")
    private String loginUrl;

    @Value("${usuarios.servicio.login.usuario}")
    private String loginUsuario;

    @Value("${usuarios.servicio.login.contrasena}")
    private String loginContrasena;

    private String cachedToken;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RequestInterceptor requestTokenBearerInterceptor(RestTemplate restTemplate) {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                // Si el token es nulo o ha expirado, obtén uno nuevo
                if (cachedToken == null || tokenExpirado(cachedToken)) {
                    cachedToken = obtenerToken(restTemplate);
                }
                if (cachedToken != null && !cachedToken.isEmpty()) {
                    requestTemplate.header("Authorization", "Bearer " + cachedToken);
                }
            }

            private String obtenerToken(RestTemplate restTemplate) {
                // Preparar las credenciales en un Map
                Map<String, String> loginBody = Map.of(
                        "correo", loginUsuario,
                        "contrasena", loginContrasena
                );
                // Realizar la solicitud POST al endpoint de login
                Map<String, String> response = restTemplate.postForObject(loginUrl, loginBody, Map.class);
                return response != null ? response.get("token") : null;
            }

            private boolean tokenExpirado(String token) {
                return false;
            }
        };
    }
}

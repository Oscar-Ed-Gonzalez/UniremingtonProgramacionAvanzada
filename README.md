# Librería App

Librería App es una aplicación distribuida diseñada para gestionar eficientemente el inventario y el proceso de ventas de una tienda de libros. El sistema facilita la administración de libros, usuarios, y pedidos, permitiendo ampliar su funcionalidad a través de un enfoque modular y escalable.

## Descripción General

Librería App fue concebida para simplificar la gestión integral de una libreria, abarcando el registro y mantenimiento de libros, el control de usuarios y la administración de pedidos. La aplicación ofrece:

- **Gestión de Inventario:** Registro, actualización y consulta de libros y otros recursos.
- **Control de Usuarios:** Administración de cuentas, roles y permisos.
- **Procesos de Pedidos:** Mecanismos para solicitar pedidos.


El sistema está orientado a soportar escenarios de alta demanda y se diseñó con un enfoque en la modularidad, facilitando la integración de nuevos servicios y funcionalidades.

## Arquitectura Distribuida

El enfoque distribuido de Librería App se basa en la implementación de una arquitectura de microservicios, permitiendo que cada componente del sistema opere de manera autónoma y se comunique con otros módulos mediante APIs REST. Este diseño aporta las siguientes ventajas:

- **Escalabilidad Horizontal:** Permite el escalamiento independiente de cada servicio en función de la demanda.
- **Alta Disponibilidad y Resiliencia:** La separación de responsabilidades minimiza el impacto ante fallos en componentes individuales.
- **Mantenimiento y Actualización Sencilla:** Los servicios pueden actualizarse o modificarse sin interrumpir el funcionamiento global del sistema.
- **Integración de Servicios Externos:** Facilidad para integrar servicios de terceros mediante interfaces bien definidas.

La arquitectura distribuida se compone de los siguientes módulos (ejemplo):
- **Servicio de Libros:** Encargado de las operaciones CRUD sobre el inventario.
- **Servicio de Usuarios:** Maneja el registro, autenticación y autorización de los usuarios.
- **Servicio de pedidos:** Controla el flujo de solicitudes, aprobaciones y devoluciones.

Este diseño permite, por ejemplo, desplegar cada microservicio en contenedores (Docker) o en nodos independientes en entornos cloud, aprovechando los beneficios de las plataformas de orquestación como Kubernetes.

## Tecnologías Utilizadas

A continuación se listan algunas de las tecnologías empleadas en este proyecto (ajustar según corresponda):

- **Backend:** [ Java (Spring Boot)]
- **Base de Datos:** [H2]
- **Contenedorización:** Docker para encapsular cada servicio.

## Requisitos e Instalación

### Requisitos Previos

- [Docker](https://www.docker.com/) y [Docker Compose](https://docs.docker.com/compose/) 
- [Java 21, Maven y Spring Boot]

### Instalación

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/Oscar-Ed-Gonzalez/UniremingtonProgramacionAvanzada.git (usar rama parcial2-microservicios)
   cd libreria-app

### Ejecucion 

    ejecute cada microservicio, asegurese de ejecutar config y eureka server primero


### Pruebas

Para ejecutar todas las pruebas desde tu entorno local, usa el siguiente comando en la raíz de cada microservicio:

mvn test


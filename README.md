# Libreria App

Este proyecto es una API REST desarrollada con Spring Boot que permite gestionar libros de manera muy basica. 
Se implementó siguiendo buenas prácticas de desarrollo y se incluyen pruebas unitarias e integración para garantizar el correcto funcionamiento de la aplicación.

## Características

API REST: Permite crear, obtener y eliminar libros.

Persistencia: Utiliza una base de datos en memoria (H2) para desarrollo y pruebas.

Pruebas Unitarias: Se validan los servicios y la lógica de negocio utilizando JUnit y Mockito.

Pruebas de Integración: Se testean los endpoints REST con MockMvc.

CI/CD: Pipeline configurado en GitHub Actions para ejecutar las pruebas automáticamente en cada push o pull request a la rama parcial-1b.

## Requisitos

Java 21
Maven

## Cómo Ejecutar la Aplicación

1. Clona el repositorio:
git clone https://github.com/Oscar-Ed-Gonzalez/UniremingtonProgramacionAvanzada.git

2. Entra en el directorio del proyecto:
cd UniremingtonProgramacionAvanzada

3. Ejecuta la aplicación:
mvn spring-boot:run

La aplicación se iniciará en http://localhost:8080. Se debe asegurar que el puerto este disponible.

## Cómo Correr las Pruebas

Localmente:

Para ejecutar todas las pruebas desde tu entorno local, usa el siguiente comando en la raíz del proyecto:

mvn test

Este comando ejecuta las pruebas unitarias (servicios) y las pruebas de integración.

En GitHub Actions:

El pipeline de GitHub Actions está configurado para que se ejecute automáticamente cuando se realice un push o se abra un pull request a la rama parcial-1b. Para verificar la ejecución:

1. Ingresa al repositorio en GitHub.

2. Haz clic en la pestaña Actions para ver el historial y estado de las ejecuciones del pipeline.

3. Cada ejecución mostrará si las pruebas han pasado o si hubo algún error.

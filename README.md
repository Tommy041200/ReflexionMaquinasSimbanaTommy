# Proyecto: Reflexión y Scripting Dinámico (Groovy)

Este proyecto aplica conceptos de **Arquitectura de Software** para cargar figuras geométricas de forma dinámica.

## Tecnologías utilizadas
* **Java 19**
* **Java Reflection API**: Para instanciar clases nativas por nombre.
* **Groovy 4.0**: Para compilar scripts en tiempo de ejecución.
* **Maven**: Para la gestión de dependencias.

## Funcionamiento
1. El sistema intenta cargar una clase nativa (ej. `Cuadrado`).
2. Si no la encuentra, busca un script de **Groovy** predefinido.
3. El motor de Groovy compila el código "al vuelo" y lo ejecuta como si fuera Java puro.
reflection -> src -> main -> java
##  Autor
* **Tommy Simbana**

package org.example.productos.models.complementos.models

/**
 * Enumera los tipos de bebidas disponibles.
 *
 * @property precio Precio de la bebida.
 * @constructor Crea un objeto enum de tipo Bebida con su respectivo precio.
 * @param precio Precio de la bebida.
 * @since 1.0
 * @author Natalia Gonzalez
 */
enum class Bebida(val precio: Double) {
    AGUA(2.00), REFRESCO(3.00)
}

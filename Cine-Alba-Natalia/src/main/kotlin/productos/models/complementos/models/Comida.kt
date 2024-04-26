package org.example.productos.models.complementos.models

/**
 * Enumera los tipos de comida disponibles.
 *
 * @property precio Precio de la comida.
 * @constructor Crea un objeto enum de tipo Comida con su respectivo precio.
 * @param precio Precio de la comida.
 * @since 1.0
 * @author Natalia Gonzalez
 */
enum class Comida(val precio: Double) {
    PALOMITAS(3.00), FRUTOS_SECOS(2.00), PATATAS(2.00)
}

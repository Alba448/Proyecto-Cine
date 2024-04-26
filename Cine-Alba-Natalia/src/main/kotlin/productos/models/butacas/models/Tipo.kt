package org.example.productos.models.butacas.models

/**
 * Enum class que representa los diferentes tipos de butacas, junto con sus precios asociados.
 *
 * @property precio El precio asociado al tipo de butaca.
 * @constructor Crea una nueva instancia de la clase Tipo.
 * @since 1.0
 * @author Natalia Gonzalez
 */
enum class Tipo(val precio: Double) {
    /** Butaca normal con precio estándar. */
    NORMAL(5.00),

    /** Butaca VIP con precio premium. */
    VIP(8.00)
}

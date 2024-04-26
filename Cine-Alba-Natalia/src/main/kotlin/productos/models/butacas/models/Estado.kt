package org.example.productos.models.butacas.models

/**
 * Enum class que representa los diferentes estados de una butaca.
 *
 * @author Natalia Gonzalez
 * @since 1.0
 */
enum class Estado {
    /** Butaca activa y disponible para ser ocupada. */
    ACTIVA,

    /** Butaca en mantenimiento y no disponible para su uso. */
    EN_MANTENIMIENTO,

    /** Butaca fuera de servicio y no disponible para su uso. */
    FUERA_DE_SERVICIO
}

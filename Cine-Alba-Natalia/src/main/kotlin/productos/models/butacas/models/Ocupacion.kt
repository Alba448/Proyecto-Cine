package org.example.productos.models.butacas.models

/**
 * Enum class que representa los diferentes estados de ocupación de una butaca.
 *
 * @author Natalia Gonzalez
 * @since 1.0
 */
enum class Ocupacion {
    /** Butaca libre y disponible para ser ocupada. */
    LIBRE,

    /** Butaca en reserva y pendiente de confirmación de ocupación. */
    EN_RESERVA,

    /** Butaca ocupada por un cliente. */
    OCUPADA
}

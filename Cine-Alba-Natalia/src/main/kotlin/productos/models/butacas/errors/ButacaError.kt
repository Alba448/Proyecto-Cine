package org.example.productos.models.butacas.errors

/**
 * Clase sellada que representa los posibles errores relacionados con las butacas.
 *
 * @param message El mensaje de error asociado.
 * @since 1.0
 * @author Natalia Gonzalez
 */
sealed class ButacaError(val message: String) {
    /**
     * Error que indica que no se encontró la butaca.
     *
     * @param message El mensaje de error asociado.
     */
    class ButacaNoEncontrada(message: String) : ButacaError(message)

    /**
     * Error que indica que la butaca no es válida.
     *
     * @param message El mensaje de error asociado.
     */
    class ButacaNoValida(message: String) : ButacaError(message)

    /**
     * Error que indica que la butaca no se pudo actualizar.
     *
     * @param message El mensaje de error asociado.
     */
    class ButacaNoActualizada(message: String) : ButacaError(message)

    /**
     * Error que indica que la butaca no se pudo eliminar.
     *
     * @param message El mensaje de error asociado.
     */
    class ButacaNoEliminada(message: String) : ButacaError(message)

    /**
     * Error que indica que la butaca no está activa.
     *
     * @param message El mensaje de error asociado.
     */
    class ButacaNoActiva(message: String) : ButacaError(message)

    class ButacaNoReservada(message: String) : ButacaError(message)
}

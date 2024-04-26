package org.example.productos.models.complementos.errors

/**
 * Clase sellada que representa los posibles errores relacionados con los complementos.
 *
 * @property message Mensaje de error asociado al error.
 * @constructor Crea una instancia de ComplementoError con el mensaje proporcionado.
 * @author Natalia Gonzalez
 * @since 1.0
 */
sealed class ComplementoError(val message: String) {
    /**
     * Error que indica que no se encontró el complemento.
     *
     * @param message Mensaje de error.
     */
    class ComplementoNoEncontrado(message: String) : ComplementoError(message)

    /**
     * Error que indica que el complemento no es válido.
     *
     * @param message Mensaje de error.
     */
    class ComplementoNoValido(message: String) : ComplementoError(message)

    /**
     * Error que indica que no se pudo actualizar el complemento.
     *
     * @param message Mensaje de error.
     */
    class ComplementoNoActualizado(message: String) : ComplementoError(message)

    /**
     * Error que indica que no se pudo eliminar el complemento.
     *
     * @param message Mensaje de error.
     */
    class ComplementoNoEliminado(message: String) : ComplementoError(message)
}

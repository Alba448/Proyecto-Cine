package org.example.productos.errors

/**
 * Clase sellada que representa errores relacionados con productos.
 *
 * Esta clase sellada define diferentes tipos de errores relacionados con operaciones de productos, cada uno con un mensaje de error asociado.
 *
 * @property message Mensaje de error asociado al tipo de error.
 * @constructor Crea una instancia de ProductoError con el mensaje especificado.
 * @param message Mensaje de error asociado al tipo de error.
 * @since 1.0
 * @author Natalia Gonzalez
 */
sealed class ProductoError(val message: String) {
    /**
     * Error que indica que el producto no fue encontrado.
     */
    class ProductoNoEncontrado(message: String) : ProductoError(message)

    /**
     * Error que indica que el producto no es válido.
     */
    class ProductoNoValido(message: String) : ProductoError(message)

    /**
     * Error que indica que el producto no pudo ser actualizado.
     */
    class ProductoNoActualizado(message: String) : ProductoError(message)

    /**
     * Error que indica que el producto no pudo ser eliminado.
     */
    class ProductoNoEliminado(message: String) : ProductoError(message)

    /**
     * Error relacionado con almacenamiento de productos.
     */
    class ProductoStorageError(message: String) : ProductoError(message)
}

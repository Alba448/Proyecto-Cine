package org.example.ventas.exceptions
/**
 * @author Alba Garcia
 */
/**
 * Clase sellada que representa excepciones relacionadas con las ventas.
 * @param message el mensaje que describe la excepción
 */
sealed class VentaExceptions(message: String) : Exception(message) {
    /**
     * Excepción que indica que no se ha encontrado una venta.
     * @param message el mensaje que describe la excepción
     */
    class VentaNoEncontrada(message: String) : VentaExceptions(message)

    /**
     * Excepción que indica que la venta no es válida.
     * @param message el mensaje que describe la excepción
     */
    class VentaNoValida(message: String) : VentaExceptions(message)

    /**
     * Excepción que indica que la venta no se ha almacenado correctamente.
     * @param message el mensaje que describe la excepción
     */
    class VentaNoAlmacenada(message: String) : VentaExceptions(message)
}
package org.example.productos.models

import java.time.LocalDateTime

/**
 * Clase abstracta base para representar un producto.
 *
 * Esta clase abstracta define las propiedades básicas comunes a todos los productos, como la fecha de creación, la fecha de última actualización y el estado de eliminación.
 *
 * @property createdAt Fecha y hora de creación del producto. Por defecto, es la fecha y hora actual.
 * @property updatedAt Fecha y hora de la última actualización del producto. Por defecto, es la fecha y hora actual.
 * @property isDeleted Indica si el producto ha sido eliminado. Por defecto, es false.
 * @constructor Crea una instancia de Producto con las propiedades especificadas o valores por defecto.
 * @param createdAt Fecha y hora de creación del producto.
 * @param updatedAt Fecha y hora de la última actualización del producto.
 * @param isDeleted Indica si el producto ha sido eliminado.
 * @since 1.0
 * @author Natalia Gonzalez
 */
abstract class Producto(
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val isDeleted: Boolean = false
)

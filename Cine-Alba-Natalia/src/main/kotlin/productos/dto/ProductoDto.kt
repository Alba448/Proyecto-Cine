package org.example.productos.dto

import kotlinx.serialization.Serializable
import java.time.LocalDateTime

/**
 * DTO (Data Transfer Object) para representar un producto.
 *
 * Esta clase representa un objeto de transferencia de datos (DTO) para un producto, con información sobre su fecha de creación,
 * última actualización y estado de eliminación.
 *
 * @property createdAt Fecha y hora de creación del producto. Por defecto, es la fecha y hora actual.
 * @property updatedAt Fecha y hora de la última actualización del producto. Por defecto, es la fecha y hora actual.
 * @property isDeleted Indica si el producto ha sido eliminado. Por defecto, es false.
 * @constructor Crea una instancia de ProductoDto con las propiedades especificadas o valores por defecto.
 * @param createdAt Fecha y hora de creación del producto.
 * @param updatedAt Fecha y hora de la última actualización del producto.
 * @param isDeleted Indica si el producto ha sido eliminado.
 * @since 1.0
 * @author Natalia Gonzalez
 */
@Serializable
data class ProductoDto(
    val createdAt: String = LocalDateTime.now().toString(),
    val updatedAt: String = LocalDateTime.now().toString(),
    val isDeleted: Boolean = false
)

package org.example.ventas.dto

import kotlinx.serialization.Serializable
import org.example.productos.dto.ProductoDto

/**
 * @author Alba Garcia
 */
/**
 * Clase de transferencia de datos (DTO) para representar una línea de venta.
 * @property id el identificador único de la línea de venta
 * @property producto el producto vendido
 * @property cantidad la cantidad de productos vendidos
 * @property precio el precio unitario del producto
 * @property createdAt la fecha y hora en que se creó la línea de venta en formato de cadena de texto
 * @property updatedAt la fecha y hora en que se actualizó la línea de venta en formato de cadena de texto
 */
@Serializable
data class LineaVentaDto(
    val id: String,
    val producto: ProductoDto,
    val cantidad: Int,
    val precio: Double,
    val createdAt: String,
    val updatedAt: String,
)
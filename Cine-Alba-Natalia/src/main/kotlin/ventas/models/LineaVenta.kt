package org.example.ventas.models

import org.example.productos.models.Producto
import java.time.LocalDateTime
/**
 * @author Alba Garcia
 */
/**
 * Clase que representa una línea de venta.
 * @property id el identificador único de la línea de venta
 * @property producto el producto asociado a la línea de venta
 * @property cantidad la cantidad de productos vendidos
 * @property precio el precio unitario del producto
 * @property createdAt la fecha y hora en que se creó la línea de venta (por defecto: la fecha y hora actual)
 * @property updatedAt la fecha y hora en que se actualizó la línea de venta (por defecto: la fecha y hora actual)
 */
data class LineaVenta(
    val id: Long,
    val producto: Producto,
    val cantidad: Int,
    val precio: Double,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
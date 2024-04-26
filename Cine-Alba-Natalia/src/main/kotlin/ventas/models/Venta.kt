package org.example.ventas.models

import org.example.socios.models.Socio
import java.time.LocalDateTime
/**
 * @author Alba Garcia
 */
/**
 * Clase que representa una venta.
 * @property id el identificador único de la venta
 * @property socio el socio asociado a la venta
 * @property lineas la lista de líneas de venta asociadas a la venta
 * @property createdAt la fecha y hora en que se creó la venta (por defecto: la fecha y hora actual)
 * @property updatedAt la fecha y hora en que se actualizó la venta (por defecto: la fecha y hora actual)
 * @property total el total de la venta calculado como la suma de los precios de las líneas de venta
 */
data class Venta(
    val id: Long,
    val socio: Socio,
    val lineas: List<LineaVenta>,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    val total: Double
        get() = lineas.sumOf { it.precio * it.cantidad }

    override fun toString(): String {
        return "Venta(id=$id, socio=$socio, lineas=$lineas, createdAt=$createdAt, updatedAt=$updatedAt, total=$total)"
    }
}
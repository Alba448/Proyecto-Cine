package org.example.ventas.dto

import kotlinx.serialization.Serializable
import org.example.socios.dto.SocioDto

/**
 * @author Alba Garcia
 */
/**
 * Clase de transferencia de datos (DTO) para representar una venta.
 * @property id el identificador único de la venta
 * @property socio el socio asociado a la venta
 * @property lineas la lista de líneas de venta asociadas a la venta
 * @property total el total de la venta
 * @property createdAt la fecha y hora en que se creó la venta en formato de cadena de texto
 * @property updatedAt la fecha y hora en que se actualizó la venta en formato de cadena de texto
 */
@Serializable
data class VentaDto(
    val id: String,
    val socio: SocioDto,
    val lineas: List<LineaVentaDto>,
    val total: Double,
    val createdAt: String,
    val updatedAt: String,
)
package org.example.socios.dto

import kotlinx.serialization.Serializable
/**
 * @author Alba Garcia
 */
/**
 * Clase de transferencia de datos (DTO) para representar un Socio.
 * @property id el identificador único del socio
 * @property nombre el nombre del socio
 * @property createdAt la fecha y hora en que se creó el socio en formato de cadena de texto
 * @property updatedAt la fecha y hora en que se actualizó el socio en formato de cadena de texto
 * @property isDeleted indica si el socio ha sido eliminado (por defecto: false)
 */
@Serializable
data class SocioDto(
    val id: Long,
    val nombre: String,
    val createdAt: String,
    val updatedAt: String,
    val isDeleted: Boolean = false
)
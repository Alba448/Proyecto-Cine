package org.example.socios.models

import java.time.LocalDateTime
/**
 * @author Alba Garcia
 */
/**
 * Clase que representa a un socio.
 * @property id el identificador único del socio
 * @property nombre el nombre del socio
 * @property createdAt la fecha y hora en que se creó el socio (por defecto: la fecha y hora actual)
 * @property updatedAt la fecha y hora en que se actualizó por última vez el socio (por defecto: la fecha y hora actual)
 * @property isDeleted indica si el socio ha sido eliminado (por defecto: false)
 */
data class Socio(
    val id: Long,
    val nombre: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val isDeleted: Boolean = false
)
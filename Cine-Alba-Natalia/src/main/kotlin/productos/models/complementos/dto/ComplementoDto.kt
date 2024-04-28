package org.example.productos.models.complementos.dto

import java.time.LocalDateTime

/**
 * DTO para representar un complemento.
 *
 * @author Natalia Gonzalez
 * @since 1.0
 */
data class ComplementoDto(
    val id: Long = -1,
    val bebida: String,
    val comida: String,
    val otros: String,
    val stock: String,
    val createdAt: String = LocalDateTime.now().toString(),
    val updatedAt: String = LocalDateTime.now().toString(),
    val isDeleted: Boolean = false
)

package org.example.productos.models.complementos.dto

/**
 * DTO para representar un complemento.
 *
 * @author Natalia Gonzalez
 * @since 1.0
 */
data class ComplementoDto(
    val id: String,
    val bebida: String,
    val comida: String,
    val otros: String,
    val stock: String,
    val createdAt: String,
    val updatedAt: String,
    val isDeleted: String
)

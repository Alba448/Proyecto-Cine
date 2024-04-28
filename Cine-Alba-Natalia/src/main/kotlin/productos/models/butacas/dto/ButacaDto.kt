package org.example.productos.models.butacas.dto

/**
 * DTO (Data Transfer Object) para representar una butaca.
 *
 * @param id El ID de la butaca.
 * @param fila La fila de la butaca.
 * @param columna La columna de la butaca.
 * @param estado El estado de la butaca.
 * @param ocupacion La ocupación de la butaca.
 * @param tipo El tipo de butaca.
 * @param createdAt La fecha y hora de creación de la butaca.
 * @param updatedAt La fecha y hora de actualización de la butaca.
 * @param isDeleted Indica si la butaca ha sido eliminada.
 * @since 1.0
 * @author Natalia Gonzalez
 */
data class ButacaDto(
    val id: String,
    val fila: String,
    val columna: String,
    val estado: String,
    val ocupacion: String,
    val tipo: String,
    val createdAt: String,
    val updatedAt: String,
    val isDeleted: String
)

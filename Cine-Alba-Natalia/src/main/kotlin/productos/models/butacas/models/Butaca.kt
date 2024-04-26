package org.example.productos.models.butacas.models

import org.example.productos.models.Producto
import java.time.LocalDateTime

/**
 * Data class que representa las butacas del cine.
 *
 * @property id identificador único de la butaca.
 * @property fila fila de la butaca.
 * @property columna columna de la butaca.
 * @property estado variable que llama a la enum class Estado.
 * @property ocupacion variable que llama a la enum class Ocupacion.
 * @property tipo variable que llama a la enum class Tipo.
 * @property createdAt variable que representa la fecha y hora de creacion de la butaca.
 * @property updatedAt variable que representa la fecha y hora de actualizacion de la butaca.
 * @property isDeleted variable que representa si la butaca está eliminada(borrado lógico).
 * @constructor Crea una nueva instancia de la clase Butaca.
 * @since 1.0
 * @author Natalia Gonzalez
 */
class Butaca(
    val id: Long = -1,
    val fila: String = "",
    val columna: String = "",
    val estado: Estado = Estado.ACTIVA,
    var ocupacion: Ocupacion = Ocupacion.LIBRE,
    val tipo: Tipo = Tipo.NORMAL,
    createdAt: LocalDateTime = LocalDateTime.now(),
    updatedAt: LocalDateTime = LocalDateTime.now(),
    isDeleted: Boolean = false
) : Producto(createdAt, updatedAt, isDeleted)

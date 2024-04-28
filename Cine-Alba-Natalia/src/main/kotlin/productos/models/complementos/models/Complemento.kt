package org.example.productos.models.complementos.models

import org.example.productos.models.Producto
import java.time.LocalDateTime

/**
 * Data class que representa los complementos a elegir.
 *
 * @property id Identificador único del complemento.
 * @property bebida Variable que representa el tipo de bebida del complemento.
 * @property comida Variable que representa el tipo de comida del complemento.
 * @property otros Variable que representa otros tipos de complementos.
 * @property stock Cantidad disponible en stock del complemento.
 * @constructor Crea un objeto de tipo Complemento con sus respectivas propiedades.
 * @param id Identificador único del complemento.
 * @param bebida Tipo de bebida del complemento.
 * @param comida Tipo de comida del complemento.
 * @param otros Otros tipos de complementos.
 * @param stock Cantidad disponible en stock del complemento.
 * @param createdAt Fecha y hora de creación del complemento.
 * @param updatedAt Fecha y hora de última actualización del complemento.
 * @param isDeleted Indica si el complemento ha sido eliminado (borrado lógico).
 * @since 1.0
 * @author Natalia González
 */
class Complemento(
    val id: Long = -1,
    val bebida: Bebida = Bebida.AGUA,
    val comida: Comida = Comida.PALOMITAS,
    val otros: Otros = Otros.OTROS,
    val stock: Int = 0,
    createdAt: LocalDateTime = LocalDateTime.now(),
    updatedAt: LocalDateTime = LocalDateTime.now(),
    isDeleted: Boolean = false
) : Producto(createdAt, updatedAt, isDeleted)

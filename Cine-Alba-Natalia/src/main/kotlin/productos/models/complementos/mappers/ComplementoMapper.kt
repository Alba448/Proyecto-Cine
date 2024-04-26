package org.example.productos.models.complementos.mappers

import org.example.productos.models.complementos.dto.ComplementoDto
import org.example.productos.models.complementos.models.Bebida
import org.example.productos.models.complementos.models.Comida
import org.example.productos.models.complementos.models.Complemento
import org.example.productos.models.complementos.models.Otros
import java.time.LocalDateTime

/**
 * Convierte un objeto de tipo ComplementoDto a un objeto de tipo Complemento.
 *
 * @param ComplementoDto Objeto ComplementoDto a convertir.
 * @return Objeto Complemento convertido.
 * @author Natalia Gonzalez
 * @since 1.0
 */
fun ComplementoDto.toComplemento(): Complemento {
    return Complemento(
        id = this.id.toLong(),
        bebida = Bebida.valueOf(this.bebida),
        comida = Comida.valueOf(this.comida),
        otros = Otros.valueOf(this.otros),
        stock = this.stock.toInt(),
        createdAt = LocalDateTime.parse(this.createdAt),
        updatedAt = LocalDateTime.parse(this.updatedAt),
        isDeleted = this.isDeleted.toBoolean()
    )
}

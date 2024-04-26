package org.example.productos.models.butacas.mappers

import org.example.productos.models.butacas.models.Butaca
import org.example.productos.models.butacas.models.Estado
import org.example.productos.models.butacas.models.Ocupacion
import org.example.productos.models.butacas.models.Tipo
import java.time.LocalDateTime

/**
 * Función de extensión para mapear un DTO de Butaca a un objeto Butaca.
 *
 * @return El objeto Butaca mapeado.
 * @since 1.0
 * @author Natalia Gonzalez
 */
fun org.example.productos.models.butacas.dto.ButacaDto.toButaca(): Butaca {
    return Butaca(
        id = this.id.toLong(),
        fila = this.fila.toInt().toString(),
        columna = this.columna.toInt().toString(),
        estado = Estado.valueOf(this.estado),
        ocupacion = Ocupacion.valueOf(this.ocupacion),
        tipo = Tipo.valueOf(this.tipo),
        createdAt = LocalDateTime.parse(this.createdAt),
        updatedAt = LocalDateTime.parse(this.updatedAt),
        isDeleted = this.isDeleted.toBoolean()
    )
}

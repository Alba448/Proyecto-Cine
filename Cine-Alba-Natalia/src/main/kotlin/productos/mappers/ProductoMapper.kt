package org.example.productos.mappers

import org.example.productos.dto.ProductoDto
import org.example.productos.models.Producto
import java.time.LocalDateTime

/**
 * Convierte un ProductoDto en un Producto.
 *
 * Esta función de extensión convierte un ProductoDto en un Producto, mapeando las propiedades correspondientes.
 *
 * @receiver ProductoDto a ser convertido.
 * @return Producto resultante de la conversión.
 * @since 1.0
 * @author Natalia Gonzalez
 */
fun ProductoDto.toProducto(): Producto {
    return Producto(
        createdAt = LocalDateTime.parse(this.createdAt),
        updatedAt = LocalDateTime.parse(this.updatedAt),
        isDeleted = this.isDeleted.toString().toInt() == 1
    )
}

/**
 * Convierte un Producto en un ProductoDto.
 *
 * Esta función de extensión convierte un Producto en un ProductoDto, mapeando las propiedades correspondientes.
 *
 * @receiver Producto a ser convertido.
 * @return ProductoDto resultante de la conversión.
 * @since 1.0
 * @author Natalia Gonzalez
 */
fun Producto.toProductoDto(): ProductoDto {
    return ProductoDto(
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
        isDeleted = this.isDeleted
    )
}

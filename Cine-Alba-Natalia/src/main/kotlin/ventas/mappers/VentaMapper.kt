package org.example.ventas.mappers

import org.example.productos.mappers.toProductoDto
import org.example.productos.models.Producto
import org.example.socios.mappers.toSocioDto
import org.example.socios.models.Socio
import org.example.ventas.dto.LineaVentaDto
import org.example.ventas.dto.VentaDto
import org.example.ventas.models.LineaVenta
import org.example.ventas.models.Venta
/**
 * @author Alba Garcia
 */
/**
 * Convierte una instancia de LineaVentaEntity a un objeto LineaVenta.
 * @param producto el producto asociado a la línea de venta
 * @return un objeto LineaVenta con los datos de la entidad
 */
fun LineaVentaEntity.toLineaVenta(producto: Producto): LineaVenta {
    return LineaVenta(
        id = this.id,
        producto = producto,
        cantidad = this.cantidad.toInt(),
        precio = this.precio,
        createdAt = LocalDateTime.parse(this.created_at),
        updatedAt = LocalDateTime.parse(this.updated_at),
    )
}

/**
 * Convierte una instancia de VentaEntity a un objeto Venta.
 * @param socio el socio asociado a la venta
 * @param lineas la lista de líneas de venta asociadas a la venta
 * @return un objeto Venta con los datos de la entidad
 */
fun VentaEntity.toVenta(socio: Socio, lineas: List<LineaVenta>): Venta {
    return Venta(
        id = this.id,
        socio = socio,
        lineas = lineas,
        createdAt = LocalDateTime.parse(this.created_at),
        updatedAt = LocalDateTime.parse(this.updated_at),
    )
}

/**
 * Convierte una instancia de LineaVenta a un objeto LineaVentaDto.
 * @return un objeto LineaVentaDto con los datos de la línea de venta
 */
fun LineaVenta.toLineaVentaDto(): LineaVentaDto {
    return LineaVentaDto(
        id = this.id.toString(),
        producto = this.producto.toProductoDto(),
        cantidad = this.cantidad,
        precio = this.precio,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
    )
}

/**
 * Convierte una instancia de Venta a un objeto VentaDto.
 * @return un objeto VentaDto con los datos de la venta
 */
fun Venta.toVentaDto(): VentaDto {
    return VentaDto(
        id = this.id.toString(),
        socio = this.socio.toSocioDto(),
        lineas = this.lineas.map { it.toLineaVentaDto() },
        total = this.lineas.sumOf { it.precio },
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
    )
}
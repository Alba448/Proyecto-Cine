package org.example.socios.mappers

import org.example.socios.dto.SocioDto
import org.example.socios.models.Socio
/**
 * @author Alba Garcia
 */
/**
 * Convierte una instancia de SociosEntity a un objeto Socio.
 * @return un objeto Socio con los datos de la entidad
 */
fun SociosEntity.toSocio(): Socio {
    return Socio(
        id = this.id,
        nombre = this.nombre,
        createdAt = LocalDateTime.parse(this.created_at),
        updatedAt = LocalDateTime.parse(this.updated_at),
        isDeleted = this.is_deleted.toInt() == 1
    )
}

/**
 * Convierte una instancia de Socio a un objeto SocioDto.
 * @return un objeto SocioDto con los datos del socio
 */
fun Socio.toSocioDto(): SocioDto {
    return SocioDto(
        id = this.id,
        nombre = this.nombre,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
        isDeleted = this.isDeleted
    )
}
package org.example.productos.models.complementos.storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.productos.models.complementos.dto.ComplementoDto
import org.example.productos.models.complementos.errors.ComplementoError
import org.example.productos.models.complementos.mappers.toComplemento
import org.example.productos.models.complementos.models.Complemento
import org.lighthousegames.logging.logging
import java.io.File

private val logger = logging()
/**
 * Implementación concreta de [StorageComplemento] que carga complementos desde un archivo.
 *
 * @since 1.0
 * @author Natalia Gonzalez
 */
class StorageComplementoImpl : StorageComplemento {
    /**
     * Carga los complementos desde un archivo.
     *
     * @param file El archivo desde el cual cargar los complementos.
     * @return [Result] que contiene una lista de complementos cargados o un error si la carga falla.
     */
    override fun load(file: File): Result<List<Complemento>, ComplementoError> {
        logger.debug { "Cargando complementos desde fichero: ${file.absolutePath}" }
        return try {
            Ok(file.readLines().drop(1).map { line ->
                val parts = line.split(",")
                ComplementoDto(
                    bebida = parts[0],
                    comida = parts[1],
                    otros = parts[2],
                    stock = parts[3]
                ).toComplemento()
            })
        } catch (e: Exception) {
            logger.error { "Error al cargar productos desde fichero: ${file.absolutePath}. ${e.message}" }
            Err(ComplementoError.ComplementoStorageError("Error al cargar complementos desde fichero: ${file.absolutePath}. ${e.message}"))
        }
    }
}
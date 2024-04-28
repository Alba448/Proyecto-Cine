package org.example.ventas.storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.ventas.dto.VentaDto
import org.example.ventas.exceptions.VentaExceptions
import org.example.ventas.mappers.toVentaDto
import org.example.ventas.models.Venta
import org.koin.core.annotation.Named
import org.koin.core.annotation.Singleton
import org.lighthousegames.logging.logging
import java.io.File

/**
 * @author Alba Garcia
 */
private val logger = logging()
/**
 * Implementación de [VentaStorage] que exporta los datos de una venta a un archivo JSON.
 */
@Singleton
@Named("VentaStorageJson")
class VentaStorageJsonImpl : VentaStorage {
    /**
     * Exporta los datos de una venta a un archivo JSON.
     * @param venta la venta a exportar
     * @param file el archivo JSON de destino
     * @throws VentaNoAlmacenada si no se pudo almacenar la venta en formato JSON
     */
    @Throws(VentaExceptions.VentaNoAlmacenada::class)
    override fun export(venta: Venta, file: File) {
        try {
            val json = Json {
                prettyPrint = true
                ignoreUnknownKeys = true
            }
            file.writeText(json.encodeToString<VentaDto>(venta.toVentaDto()))
        } catch (e: Exception) {
            logger.error { "Error al salvar venta a archivo JSON: ${file.absolutePath}. ${e.message}" }
            throw VentaExceptions.VentaNoAlmacenada("Error al salvar venta a archivo JSON: ${file.absolutePath}. ${e.message}")
        }
    }
}
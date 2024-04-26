package org.example.ventas.storage

import com.github.michaelbull.result.Result
import org.example.ventas.exceptions.VentaExceptions
import org.example.ventas.models.Venta
import java.io.File

/**
 * @author Alba Garcia
 */
/**
 * Interfaz que define las operaciones de almacenamiento de ventas en diferentes formatos.
 */
interface VentaStorage {
    /**
     * Exporta los datos de una venta a un archivo.
     * @param venta la venta a exportar
     * @param file el archivo de destino
     * @throws VentaNoAlmacenada si no se pudo almacenar la venta en el formato especificado
     */
    @Throws(VentaExceptions.VentaNoAlmacenada::class)
    fun export(venta: Venta, file: File)
}
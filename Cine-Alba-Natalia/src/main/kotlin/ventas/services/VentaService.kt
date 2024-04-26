package org.example.ventas.services

import com.github.michaelbull.result.Result
import org.example.socios.models.Socio
import org.example.ventas.exceptions.VentaExceptions
import org.example.ventas.models.LineaVenta
import org.example.ventas.models.Venta
import java.io.File

/**
 * @author Alba Garcia
 */
/**
 * Interfaz que define las operaciones relacionadas con la manipulación de ventas en el sistema.
 */
interface VentaService {
    /**
     * Obtiene una venta por su identificador único.
     * @param id el identificador de la venta a buscar
     * @return la venta encontrada si existe, o lanza una excepción VentaNoEncontrada si no se encuentra ninguna venta con el identificador dado
     * @throws VentaNoEncontrada si no se encuentra ninguna venta con el identificador dado
     */
    fun getById(id: Long): Venta

    /**
     * Crea una nueva venta en el sistema.
     * @param venta la venta a crear
     * @return la venta creada
     * @throws VentaNoValida si la venta no es válida
     * @throws VentaNoAlmacenada si no se pudo almacenar la venta
     */
    @Throws(VentaExceptions.VentaNoValida::class, VentaExceptions.VentaNoAlmacenada::class)
    fun create(venta: Venta): Venta

    /**
     * Crea una nueva venta en el sistema con el socio y las líneas de venta especificadas.
     * @param socios el socio asociado a la venta
     * @param lineas la lista de líneas de venta asociadas a la venta
     * @return la venta creada
     * @throws VentaNoValida si la venta no es válida
     * @throws VentaNoAlmacenada si no se pudo almacenar la venta
     */
    @Throws(VentaExceptions.VentaNoValida::class, VentaExceptions.VentaNoAlmacenada::class)
    fun create(socios: Socio, lineas: List<LineaVenta>): Venta

    /**
     * Exporta los datos de una venta a un archivo JSON.
     * @param venta la venta a exportar
     * @param jsonFile el archivo JSON de destino
     * @throws VentaNoAlmacenada si no se pudo almacenar la venta en formato JSON
     */
    @Throws(VentaExceptions.VentaNoAlmacenada::class)
    fun exportToJson(venta: Venta, jsonFile: File)

    /**
     * Exporta los datos de una venta a un archivo HTML.
     * @param venta la venta a exportar
     * @param htmlFile el archivo HTML de destino
     * @throws VentaNoAlmacenada si no se pudo almacenar la venta en formato HTML
     */
    @Throws(VentaExceptions.VentaNoAlmacenada::class)
    fun exportToHtml(venta: Venta, htmlFile: File)
}
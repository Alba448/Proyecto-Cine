package org.example.ventas.services

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.andThen
import org.example.productos.repositories.ProductoRepository
import org.example.socios.models.Socio
import org.example.socios.repositories.SociosRepository
import org.example.ventas.exceptions.VentaExceptions
import org.example.ventas.models.LineaVenta
import org.example.ventas.models.Venta
import org.example.ventas.repositories.VentaRepository
import org.example.ventas.storage.VentaStorage
import org.koin.core.annotation.Named
import org.koin.core.annotation.Singleton
import org.lighthousegames.logging.logging
import java.io.File

/**
 * @author Alba Garcia
 */
private val logger = logging()
/**
 * Implementación de la interfaz VentaService que proporciona funcionalidades para manipular las ventas en el sistema.
 * @property ventasRepository el repositorio de ventas utilizado para acceder y manipular los datos de las ventas
 * @property productoRepository el repositorio de productos utilizado para validar y actualizar el stock de los productos asociados a las ventas
 * @property sociosRepository el repositorio de socios utilizado para validar la existencia del socio asociado a las ventas
 * @property ventaStorageJson el almacenamiento utilizado para exportar las ventas a formato JSON
 * @property ventaStorageHtml el almacenamiento utilizado para exportar las ventas a formato HTML
 */
@Singleton
class VentaServiceImpl (
    private val ventasRepository: VentaRepository,
    private val productoRepository: ProductoRepository,
    private val sociosRepository: SociosRepository,
    @Named("VentasStorageJson")
    private val ventaStorageJson: VentaStorage,
    @Named("VentasStorageHtml")
    private val ventaStorageHtml: VentaStorage
) : VentaService {

    /**
     * Obtiene una venta por su identificador único.
     * @param id el identificador de la venta a buscar
     * @return la venta encontrada si existe, o lanza una excepción VentaNoEncontrada si no se encuentra ninguna venta con el identificador dado
     */
    override fun getById(id: Long): Venta {
        // Implementación para obtener una venta por su id
        logger.debug { "Obteniendo venta por id: $id" }
        return ventasRepository.findById(id)
            ?: throw VentaExceptions.VentaNoEncontrada("Venta no encontrada con id: $id")
    }

    /**
     * Crea una nueva venta en el sistema.
     * @param venta la venta a crear
     * @return la venta creada
     * @throws VentaNoValida si la venta no es válida
     */
    override fun create(venta: Venta): Venta {
        // Implementación para crear una venta
        logger.debug { "Creando venta: $venta" }

        validateCliente(venta.socio)
        validateLineas(venta.lineas)
        actualizarStock(venta.lineas)

        return ventasRepository.save(venta)
    }

    /**
     * Crea una nueva venta en el sistema con el socio y las líneas de venta especificadas.
     * @param socios el socio asociado a la venta
     * @param lineas la lista de líneas de venta asociadas a la venta
     * @return la venta creada
     * @throws VentaNoValida si la venta no es válida
     */
    override fun create(socio: Socio, lineas: List<LineaVenta>): Venta {
        // Implementación para crear una venta con el socio y las líneas de venta especificadas
        logger.debug { "Creando venta con socio y lineas: $socio, $lineas" }

        validateCliente(socio)
        validateLineas(lineas)
        actualizarStock(lineas)

        return ventasRepository.save(Venta(socio = socio, lineas = lineas))
    }


    private fun validateCliente(socio: Socio) {
        logger.debug { "Validando socio: $socio" }
        sociosRepository.findById(socio.id)
            ?: throw VentaExceptions.VentaNoValida("Socio no encontrado con id: ${socio.id}")
    }

    private fun validateLineas(lineas: List<LineaVenta>) {
        logger.debug { "Validando lineas - Existen Productos: $lineas" }
        lineas.forEach {
            productoRepository.findById(it.producto.id)
                ?: throw VentaExceptions.VentaNoValida("Producto no encontrado con id: ${it.producto.id}")
        }
        logger.debug { "Validando lineas - Cantidad y Stock de productos: $lineas" }
        lineas.forEach {
            if (it.cantidad <= 0) {
                throw VentaExceptions.VentaNoValida("La cantidad de productos debe ser mayor que 0")
            }
            productoRepository.findById(it.producto.id)?.let { producto ->
                if (it.cantidad > producto.stock) {
                    throw VentaExceptions.VentaNoValida("No hay suficiente stock para el producto: ${producto.nombre}, stock: ${producto.stock} cantidad: ${it.cantidad}")
                }
            }
        }
    }

    private fun actualizarStock(lineas: List<LineaVenta>) {
        logger.debug { "Actualizando stock de productos: $lineas" }
        lineas.forEach {
            productoRepository.findById(it.producto.id)?.let { producto ->
                val updateProducto = producto.copy(stock = producto.stock - it.cantidad)
                if (productoRepository.update(producto.id, updateProducto) == null) {
                    throw VentaExceptions.VentaNoValida("Error al actualizar el stock del producto: ${producto.nombre}")
                }
            }
        }
    }

    /**
     * Exporta los datos de una venta a un archivo JSON.
     * @param venta la venta a exportar
     * @param jsonFile el archivo JSON de destino
     * @throws VentaNoAlmacenada si no se puede almacenar la venta en formato JSON
     */
    override fun exportToJson(venta: Venta, jsonFile: File) {
        logger.debug { "Exportando venta a fichero csv: $jsonFile" }
        try {
            ventaStorageJson.export(venta, jsonFile)
        } catch (e: Exception) {
            throw VentaExceptions.VentaNoAlmacenada("Error al exportar la venta a formato JSON: ${e.message}")
        }
    }

    /**
     * Exporta los datos de una venta a un archivo HTML.
     * @param venta la venta a exportar
     * @param htmlFile el archivo HTML de destino
     * @throws VentaNoAlmacenada si no se puede almacenar la venta en formato HTML
     */
    override fun exportToHtml(venta: Venta, htmlFile: File) {
        logger.debug { "Exportando venta a fichero html: $htmlFile" }
        try {
            ventaStorageHtml.export(venta, htmlFile)
        } catch (e: Exception) {
            throw VentaExceptions.VentaNoAlmacenada("Error al exportar la venta a formato HTML: ${e.message}")
        }
    }
}
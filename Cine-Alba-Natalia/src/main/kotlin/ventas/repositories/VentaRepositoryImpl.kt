package org.example.ventas.repositories

import org.example.database.service.SqlDeLightManager
import org.example.productos.repositories.ProductoRepository
import org.example.socios.repositories.SociosRepository
import org.example.ventas.models.Venta
import org.koin.core.annotation.Singleton
import org.lighthousegames.logging.logging
import java.time.LocalDateTime

private val logger = logging()
/**
 * @author Alba Garcia
 */

/**
 * Implementación de la interfaz VentaRepository que utiliza SQLDelight para acceder y manipular los datos de las ventas.
 * @property dbManager el gestor de la base de datos SQLDelight
 * @property productoRepository el repositorio de productos utilizado para obtener los productos asociados a las líneas de venta
 * @property sociosRepository el repositorio de socios utilizado para obtener el socio asociado a la venta
 */
@Singleton
class VentaRepositoryImpl(
    private val dbManager: SqlDeLightManager,
    private val productoRepository: ProductoRepository,
    private val sociosRepository: SociosRepository,
) : VentaRepository {

    private val db = dbManager.databaseQueries

    /**
     * Obtiene una lista de todas las ventas.
     * @return una lista que contiene todas las ventas
     */
    override fun findAll(): List<Venta> {
        logger.debug { "Buscando todas las ventas" }
        val ventas = mutableListOf<Venta>()
        val ventasEntities = db.selectAllVentas().executeAsList()
        ventasEntities.forEach { ventaEntity ->
            val socios = sociosRepository.findById(ventaEntity.socios_id)!!
            val lineasVenta = db.selectAllLineasVentaByVentaId(ventaEntity.id).executeAsList()
                .map { it.toLineaVenta(productoRepository.findById(it.producto_id)!!) }
            ventas.add(ventaEntity.toVenta(socios, lineasVenta))
        }
        return ventas
    }

    /**
     * Busca una venta por su identificador único.
     * @param id el identificador de la venta a buscar
     * @return la venta encontrada o null si no se encuentra ninguna venta con el identificador dado
     */
    override fun findById(id: Long): Venta? {
        // Implementación para buscar una venta por su id
        logger.debug { "Obteniendo venta por id: $id" }
        if (db.existsVenta(id.toString()).executeAsOne()) {
            val ventaEntity = db.selectVentaById(id.toString()).executeAsOne()
            val socios = sociosRepository.findById(ventaEntity.socios_id)!!
            val lineasVenta = db.selectAllLineasVentaByVentaId(ventaEntity.id).executeAsList()
                .map { it.toLineaVenta(productoRepository.findById(it.producto_id)!!) }
            return ventaEntity.toVenta(socios, lineasVenta)
        }
        return null
    }

    /**
     * Guarda una nueva venta en el repositorio.
     * @param venta la venta a guardar
     * @return la venta guardada
     */
    override fun save(venta: Venta): Venta {
        // Implementación para guardar una venta
        logger.debug { "Guardando venta: $venta" }
        db.transaction {
            db.insertVenta(
                id = venta.id.toString(),
                socios_id = venta.socio.id,
                total = venta.total,
                created_at = venta.createdAt.toString(),
                updated_at = venta.updatedAt.toString()
            )
        }
        venta.lineas.forEach {
            db.transaction {
                db.insertLineaVenta(
                    id = it.id.toString(),
                    venta_id = venta.id.toString(),
                    producto_id = it.producto.id,
                    cantidad = it.cantidad.toLong(),
                    precio = it.precio,
                    created_at = it.createdAt.toString(),
                    updated_at = it.updatedAt.toString()
                )
            }
        }
        return venta
    }

    /**
     * Actualiza una venta existente en el repositorio.
     * @param id el identificador de la venta a actualizar
     * @param venta la nueva instancia de venta
     * @return la venta actualizada o null si no se encuentra ninguna venta con el identificador dado
     */
    override fun update(id: Long, venta: Venta): Venta? {
        // Implementación para actualizar una venta
        logger.debug { "Actualizando venta por id: $id" }
        var result = this.findById(id) ?: return null
        val timeStamp = LocalDateTime.now()
        result = result.copy(
            socio= venta.socio,
            lineas = venta.lineas,
            updatedAt = timeStamp
        )

        db.updateVenta(
            socio = result.socio,
            lineas= result.lineas,
            updated_at = timeStamp.toString(),
            id = id,
        )
        return result
    }

    /**
     * Elimina una venta del repositorio.
     * @param id el identificador de la venta a eliminar
     * @return la venta eliminada o null si no se encuentra ninguna venta con el identificador dado
     */
    override fun delete(id: Long): Venta? {
        logger.debug { "Eliminando venta por id: $id" }
        val venta = findById(id) ?: return null
        db.deleteVentaById(id.toString())
        return venta
    }


}
package org.example.ventas.repositories

import org.example.ventas.models.Venta

/**
 * @author Alba Garcia
 */
/**
 * Interfaz que define las operaciones disponibles para acceder y manipular los datos de las ventas.
 */
interface VentaRepository {
    /**
     * Obtiene todas las ventas.
     * @return una lista que contiene todas las ventas
     */
    fun findAll(): List<Venta>

    /**
     * Busca una venta por su identificador único.
     * @param id el identificador de la venta a buscar
     * @return la venta encontrada o null si no se encuentra ninguna venta con el identificador dado
     */
    fun findById(id: Long): Venta?

    /**
     * Guarda una nueva venta en el repositorio.
     * @param venta la venta a guardar
     * @return la venta guardada
     */
    fun save(venta: Venta): Venta

    /**
     * Actualiza una venta existente en el repositorio.
     * @param id el identificador de la venta a actualizar
     * @param venta la nueva instancia de venta
     * @return la venta actualizada o null si no se encuentra ninguna venta con el identificador dado
     */
    fun update(id: Long, venta: Venta): Venta?

    /**
     * Elimina una venta del repositorio.
     * @param id el identificador de la venta a eliminar
     * @return la venta eliminada o null si no se encuentra ninguna venta con el identificador dado
     */
    fun delete(id: Long): Venta?
}
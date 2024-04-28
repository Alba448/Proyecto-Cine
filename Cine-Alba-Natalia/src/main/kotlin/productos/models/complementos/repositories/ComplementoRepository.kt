package org.example.productos.models.complementos.repositories

import org.example.crud.CrudRepository
import org.example.productos.models.complementos.models.Bebida
import org.example.productos.models.complementos.models.Comida
import org.example.productos.models.complementos.models.Complemento

/**
 * Repositorio para la gestión de los complementos.
 *
 * @since 1.0
 * @property findAll Función para obtener todos los complementos.
 * @property findById Función para encontrar un complemento por su ID.
 * @property findByBebida Función para encontrar complementos por tipo de bebida.
 * @property findByComida Función para encontrar complementos por tipo de comida.
 * @property findByStock Función para encontrar complementos por stock.
 * @property save Función para guardar un complemento.
 * @property update Función para actualizar un complemento.
 * @property delete Función para eliminar un complemento.
 * @param T El tipo de la entidad del repositorio.
 * @param ID El tipo del identificador de la entidad.
 * @author Natalia Gonzalez
 */
interface ComplementoRepository : CrudRepository<Complemento, Long> {
    override fun findAll(): List<Complemento>
    override fun findById(id: Long): Complemento?
    fun findByBebida(bebida: Bebida): List<Complemento>
    fun findByComida(comida: Comida): List<Complemento>
    fun findByStock(stock: Int): List<Complemento>
    override fun save(complemento: Complemento): Complemento
    override fun update(id: Long, complemento: Complemento): Complemento?
    override fun delete(id: Long): Complemento?
}
package org.example.productos.repositories

import org.example.productos.models.Producto

/**
 * Interfaz para el repositorio de productos.
 *
 * Esta interfaz define métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en productos.
 *
 * @author Natalia Gonzalez
 * @since 1.0
 */
interface ProductoRepository {
    /**
     * Recupera todos los productos.
     *
     * @return Una lista de todos los productos.
     */
    fun findAll(): List<Producto>

    /**
     * Busca un producto por su ID.
     *
     * @param id El ID del producto a buscar.
     * @return El producto encontrado, o null si no se encuentra.
     */
    fun findById(id: Long): Producto?

    /**
     * Guarda un nuevo producto o actualiza uno existente.
     *
     * @param producto El producto a guardar o actualizar.
     * @return El producto guardado o actualizado.
     */
    fun save(producto: Producto): Producto

    /**
     * Actualiza un producto existente.
     *
     * @param id El ID del producto a actualizar.
     * @param producto El nuevo estado del producto.
     * @return El producto actualizado, o null si el producto no se encuentra.
     */
    fun update(id: Long, producto: Producto): Producto?

    /**
     * Elimina un producto.
     *
     * @param id El ID del producto a eliminar.
     * @return El producto eliminado, o null si el producto no se encuentra.
     */
    fun delete(id: Long): Producto?
}

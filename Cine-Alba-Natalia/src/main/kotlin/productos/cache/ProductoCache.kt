package org.example.productos.cache

import org.example.cache.base.CacheImpl
import org.example.productos.models.Producto

/**
 * Implementación de cache para productos.
 *
 * Esta clase proporciona una implementación específica de cache para almacenar objetos de tipo Producto.
 *
 * @property size Tamaño máximo del cache. Por defecto, es 10.
 * @constructor Crea una instancia de ProductoCache con el tamaño especificado.
 * @param size Tamaño máximo del cache.
 * @since 1.0
 * @author Natalia Gonzalez
 */
class ProductoCache(size: Int = 10) : CacheImpl<Long, Producto>(size)

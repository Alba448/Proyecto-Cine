package org.example.productos.repositories

import org.example.databasemanager.DataBaseManager
import org.example.productos.models.Producto
import org.koin.core.annotation.Singleton
import org.lighthousegames.logging.logging
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Timestamp
import java.time.LocalDateTime

private val logger = logging()

/**
 * Implementación del repositorio de productos que interactúa con la base de datos.
 *
 * Esta implementación proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en productos,
 * utilizando una base de datos subyacente.
 *
 * @constructor Crea una instancia de ProductoRepositoryImpl.
 * @since 1.0
 * @author Natalia Gonzalez
 */
@Singleton
class ProductoRepositoryImpl() : ProductoRepository {

    /**
     * Convierte un ResultSet a Producto.
     */
    private fun ResultSet.toProducto(): Producto {
        return Producto(
            createdAt = getTimestamp("created_at").toLocalDateTime(),
            updatedAt = getTimestamp("updated_at").toLocalDateTime(),
            isDeleted = getBoolean("is_deleted")
        )
    }

    /**
     * Recupera todos los productos de la base de datos.
     */
    override fun findAll(): List<Producto> {
        val result = mutableListOf<Producto>()
        DataBaseManager.use { db ->
            val sql = "SELECT * FROM productos"
            val stmt = db.prepareStatement(sql)
            val rs = stmt.executeQuery()
            while (rs.next()) {
                result.add(rs.toProducto())
            }
        }
        return result
    }

    /**
     * Busca un producto por su ID en la base de datos.
     */
    override fun findById(id: Long): Producto? {
        var result: Producto? = null
        DataBaseManager.use { db ->
            val sql = "SELECT * FROM productos WHERE id = ?"
            val stmt = db.prepareStatement(sql).apply {
                setLong(1, id)
            }
            val rs = stmt.executeQuery()
            if (rs.next()) {
                result = rs.toProducto()
            }
        }
        return result
    }

    /**
     * Guarda un nuevo producto en la base de datos.
     */
    override fun save(producto: Producto): Producto {
        logger.debug { "Guardando producto: $producto" }
        val timeStamp = LocalDateTime.now()
        var result: Producto = producto
        DataBaseManager.use { db ->
            val sql =
                "INSERT INTO productos (nombre, precio, stock, categoria, created_at, updated_at, is_deleted) VALUES (?, ?, ?, ?, ?, ?, ?)"
            val stmt = db.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS).apply {
                setString(1, producto.nombre)
                setDouble(2, producto.precio)
                setInt(3, producto.stock)
                setString(4, producto.categoria.name)
                setTimestamp(5, Timestamp.valueOf(timeStamp))
                setTimestamp(6, Timestamp.valueOf(timeStamp))
                setBoolean(7, false)
            }
            val rs = stmt.executeUpdate()
            if (rs > 0) {
                val generatedKeys = stmt.generatedKeys
                if (generatedKeys.next()) {
                    result = producto.copy(
                        id = generatedKeys.getLong(1),
                        createdAt = timeStamp,
                        updatedAt = timeStamp
                    )
                }
            }
        }
        return result
    }

    /**
     * Actualiza un producto existente en la base de datos.
     */
    override fun update(id: Long, producto: Producto): Producto? {
        logger.debug { "Actualizando producto por id: $id" }
        var result: Producto = this.findById(id) ?: return null
        DataBaseManager.use { db ->
            val sql =
                "UPDATE productos SET nombre = ?, precio = ?, stock = ?, categoria = ?, updated_at = ? WHERE id = ?"
            val stmt = db.prepareStatement(sql).apply {
                setString(1, producto.nombre)
                setDouble(2, producto.precio)
                setInt(3, producto.stock)
                setString(4, producto.categoria.name)
                setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()))
                setLong(6, id)
            }
            val rs = stmt.executeUpdate()
            if (rs > 0) {
                result = producto.copy(
                    id = id,
                    updatedAt = LocalDateTime.now()
                )
            }
        }
        return result
    }

    /**
     * Elimina un producto de la base de datos.
     */
    override fun delete(id: Long): Producto? {
        logger.debug { "Borrando producto por id: $id" }
        var result: Producto = this.findById(id) ?: return null
        DataBaseManager.use { db ->
            val sql = "DELETE FROM productos WHERE id = ?"
            val stmt = db.prepareStatement(sql).apply {
                setLong(1, id)
            }
            val rs = stmt.executeUpdate()
            if (rs > 0) {
                result = result.copy(isDeleted = true)
            }
        }
        return result
    }
}

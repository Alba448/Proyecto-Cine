package org.example.productos.models.complementos.repositories

import org.example.databasemanager.DataBaseManager
import org.example.productos.models.complementos.dto.ComplementoDto
import org.example.productos.models.complementos.mappers.toComplemento
import org.example.productos.models.complementos.models.Bebida
import org.example.productos.models.complementos.models.Comida
import org.example.productos.models.complementos.models.Complemento
import org.lighthousegames.logging.logging
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Timestamp
import java.time.LocalDateTime

private val logger = logging()

/**
 * Implementación del repositorio de complementos.
 *
 * @since 1.0
 * @property findAll Función para obtener todos los complementos.
 * @property findByBebida Función para encontrar complementos por tipo de bebida.
 * @property findByComida Función para encontrar complementos por tipo de comida.
 * @property findByStock Función para encontrar complementos por stock.
 * @property findById Función para encontrar un complemento por su ID.
 * @property save Función para guardar un complemento.
 * @property update Función para actualizar un complemento.
 * @property delete Función para eliminar un complemento.
 * @author Natalia Gonzalez
 */
class ComplementoRepositoryImpl : ComplementoRepository {
    private fun ResultSet.toComplementoDto(): ComplementoDto {
        return ComplementoDto(
            id = getString("id"),
            bebida = getString("bebida"),
            comida = getString("comida"),
            otros = getString("otros"),
            stock = getString("stock"),
            createdAt = getString("created_at"),
            updatedAt = getString("updated_at"),
            isDeleted = getString("is_deleted")
        )
    }

    override fun findAll(): List<Complemento> {
        val result = mutableListOf<Complemento>()
        DataBaseManager.use { db ->
            val sql = "SELECT * FROM complementos"
            val stmt = db.prepareStatement(sql)
            val rs = stmt.executeQuery()
            while (rs.next()) {
                result.add(rs.toComplementoDto().toComplemento())
            }
        }
        return result
    }

    override fun findByBebida(bebida: Bebida): List<Complemento> {
        val result = mutableListOf<Complemento>()
        DataBaseManager.use { db ->
            val sql = "SELECT * FROM complementos WHERE bebida = ?"
            val stmt = db.prepareStatement(sql).apply {
                setString(1, bebida.name)
            }
            val rs = stmt.executeQuery()
            while (rs.next()) {
                result.add(rs.toComplementoDto().toComplemento())
            }
        }
        return result
    }

    override fun findByComida(comida: Comida): List<Complemento> {
        val result = mutableListOf<Complemento>()
        DataBaseManager.use { db ->
            val sql = "SELECT * FROM complementos WHERE comida = ?"
            val stmt = db.prepareStatement(sql).apply {
                setString(1, comida.name)
            }
            val rs = stmt.executeQuery()
            while (rs.next()) {
                result.add(rs.toComplementoDto().toComplemento())
            }
        }
        return result
    }

    override fun findByStock(stock: Int): List<Complemento> {
        val result = mutableListOf<Complemento>()
        DataBaseManager.use { db ->
            val sql = "SELECT * FROM complementos WHERE stock = ?"
            val stmt = db.prepareStatement(sql).apply {
                setInt(1, stock)
            }
            val rs = stmt.executeQuery()
            while (rs.next()) {
                result.add(rs.toComplementoDto().toComplemento())
            }
        }
        return result
    }

    override fun findById(id: Long): Complemento? {
        var result: Complemento? = null
        DataBaseManager.use { db ->
            val sql = "SELECT * FROM complementos WHERE id = ?"
            val stmt = db.prepareStatement(sql).apply {
                setLong(1, id)
            }
            val rs = stmt.executeQuery()
            if (rs.next()) {
                result = rs.toComplementoDto().toComplemento()
            }
        }
        return result
    }

    override fun save(complemento: Complemento): Complemento {
        logger.debug { "Guardando complemento: $complemento" }
        val timeStamp = LocalDateTime.now()
        var result: Complemento = complemento
        DataBaseManager.use { db ->
            val sql =
                "INSERT INTO complementos (bebida, comida, otros, stock, created_at, updated_at, is_deleted) VALUES (?, ?, ?, ?, ?, ?, ?)"
            val stmt = db.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS).apply {
                setObject(1, complemento.bebida)
                setObject(2, complemento.comida)
                setObject(3, complemento.otros)
                setInt(4, complemento.stock)
                setTimestamp(5, Timestamp.valueOf(timeStamp))
                setTimestamp(6, Timestamp.valueOf(timeStamp))
                setBoolean(7, false)
            }
            val rs = stmt.executeUpdate()
            if (rs > 0) {
                val generatedKeys = stmt.generatedKeys
                if (generatedKeys.next()) {
                    result = complemento.copy(
                        id = generatedKeys.getLong(1),
                        createdAt = timeStamp,
                        updatedAt = timeStamp
                    )
                }
            }
        }
        return result
    }

    override fun update(id: Long, complemento: Complemento): Complemento? {
        logger.debug { "Actualizando complemento por id: $id" }
        var result: Complemento = this.findById(id) ?: return null
        DataBaseManager.use { db ->
            val sql =
                "UPDATE complementos SET bebida = ?, comida = ?, otros = ?, stock = ?, updated_at = ? WHERE id = ?"
            val stmt = db.prepareStatement(sql).apply {
                setObject(1, complemento.bebida)
                setObject(2, complemento.comida)
                setObject(3, complemento.otros)
                setInt(4, complemento.stock)
                setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()))
                setLong(6, id)
            }
            val rs = stmt.executeUpdate()
            if (rs > 0) {
                result = complemento.copy(
                    id = id,
                    updatedAt = LocalDateTime.now()
                )
            }
        }
        return result
    }

    override fun delete(id: Long): Complemento? {
        logger.debug { "Borrando complemento por id: $id" }
        var result: Complemento = this.findById(id) ?: return null
        DataBaseManager.use { db ->
            val sql = "DELETE FROM complementos WHERE id = ?"
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
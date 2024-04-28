package org.example.productos.models.butacas.repositories

import org.example.databasemanager.DataBaseManager
import org.example.productos.models.butacas.mappers.toButaca
import org.example.productos.models.butacas.models.Butaca
import org.example.productos.models.butacas.models.Estado
import org.example.productos.models.butacas.models.Ocupacion
import org.example.productos.models.butacas.models.Tipo
import org.lighthousegames.logging.logging
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Timestamp
import java.time.LocalDateTime

private val logger = logging()

/**
 * Implementación de la interfaz `ButacaRepository`.
 *
 * @author Natalia Gonzalez
 * @since 1.0
 */
class ButacaRepositoryImpl : ButacaRepository {
    /**
     * Convierte un ResultSet a un DTO de Butaca.
     */
    private fun ResultSet.toButacaDto(): org.example.productos.models.butacas.dto.ButacaDto {
        return org.example.productos.models.butacas.dto.ButacaDto(
            id = getString("id"),
            fila = getString("fila"),
            columna = getString("columna"),
            estado = getString("estado"),
            ocupacion = getString("ocupacion"),
            tipo = getString("tipo"),
            createdAt = getString("created_at"),
            updatedAt = getString("updated_at"),
            isDeleted = getString("is_deleted")
        )
    }

    override fun reservarButaca(butaca: Butaca): Butaca? {
        logger.debug { "Reservando butaca..." }

        var idButaca : String
        do {
            println("Inserte la butaca que quiere comprar (Por ejemplo: A4):")
            idButaca = readln()

            if (Butaca().ocupacion != Ocupacion.LIBRE){
                println("La butaca ya está ocupada. Por favor seleccione otra diferente.")
            } else if (Butaca().estado != Estado.ACTIVA){
                println("La butaca no está disponible. Por favor seleccione otra diferente.")
            }
        }while (Butaca().ocupacion != Ocupacion.LIBRE && Butaca().estado != Estado.ACTIVA)

        if (butaca.id.toString() == idButaca){
            butaca.ocupacion = Ocupacion.OCUPADA
            update(idButaca, butaca)
        }
        return butaca
    }

    override fun devolverButaca(butaca: Butaca): Butaca? {
        logger.debug{"Devolviendo butaca..."}

        var idButaca : String
        do {
            println("Inserte la butaca que quiere devolver (Por ejemplo: A4):")
            idButaca = readln()

            if (Butaca().ocupacion == Ocupacion.LIBRE){
                println("La butaca no ha sido comprada. Por favor introduzca otra diferente.")
            } else if (Butaca().estado != Estado.ACTIVA){
                println("La butaca no está disponible. Por favor introduzca otra diferente.")
            }
        }while (Butaca().ocupacion == Ocupacion.LIBRE && Butaca().estado != Estado.ACTIVA)

        if (butaca.id.toString() == idButaca){
            butaca.ocupacion = Ocupacion.LIBRE
            update(idButaca, butaca)
        }
        return butaca
    }

    /**
     * Obtiene todas las butacas de la base de datos.
     */
    override fun findAll(): List<Butaca> {
        val result = mutableListOf<Butaca>()
        DataBaseManager.use { db ->
            val sql = "SELECT * FROM butacas"
            val stmt = db.prepareStatement(sql)
            val rs = stmt.executeQuery()
            while (rs.next()) {
                result.add(rs.toButacaDto().toButaca())
            }
        }
        return result
    }

    /**
     * Obtiene una butaca por su ID.
     */
    override fun findById(id: String): Butaca? {
        var result: Butaca? = null
        DataBaseManager.use { db ->
            val sql = "SELECT * FROM butacas WHERE id = ?"
            val stmt = db.prepareStatement(sql).apply {
                setString(1, id)
            }
            val rs = stmt.executeQuery()
            if (rs.next()) {
                result = rs.toButacaDto().toButaca()
            }
        }
        return result
    }

    /**
     * Obtiene butacas por tipo.
     */
    override fun findByTipo(tipo: Tipo): List<Butaca> {
        val result = mutableListOf<Butaca>()
        DataBaseManager.use { db ->
            val sql = "SELECT * FROM butacas WHERE tipo = ?"
            val stmt = db.prepareStatement(sql).apply {
                setString(1, tipo.name)
            }
            val rs = stmt.executeQuery()
            while (rs.next()) {
                result.add(rs.toButacaDto().toButaca())
            }
        }
        return result
    }

    /**
     * Obtiene butacas por estado.
     */
    override fun findByEstado(estado: Estado): List<Butaca> {
        val result = mutableListOf<Butaca>()
        DataBaseManager.use { db ->
            val sql = "SELECT * FROM butacas WHERE estado = ?"
            val stmt = db.prepareStatement(sql).apply {
                setString(1, estado.name)
            }
            val rs = stmt.executeQuery()
            while (rs.next()) {
                result.add(rs.toButacaDto().toButaca())
            }
        }
        return result
    }

    /**
     * Obtiene butacas por ocupación.
     */
    override fun findByOcupacion(ocupacion: Ocupacion): List<Butaca> {
        val result = mutableListOf<Butaca>()
        DataBaseManager.use { db ->
            val sql = "SELECT * FROM butacas WHERE ocupacion = ?"
            val stmt = db.prepareStatement(sql).apply {
                setString(1, ocupacion.name)
            }
            val rs = stmt.executeQuery()
            while (rs.next()) {
                result.add(rs.toButacaDto().toButaca())
            }
        }
        return result
    }

    /**
     * Guarda una butaca en la base de datos.
     */
    override fun save(butaca: Butaca): Butaca {
        logger.debug { "Guardando butaca: $butaca" }
        val timeStamp = LocalDateTime.now()
        var result: Butaca = butaca
        DataBaseManager.use { db ->
            val sql =
                "INSERT INTO butacas (fila, columna, estado, ocupacion, tipo, created_at, updated_at, is_deleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            val stmt = db.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS).apply {
                setString(1, butaca.fila)
                setString(2, butaca.columna)
                setObject(3, butaca.estado)
                setObject(4, butaca.ocupacion)
                setObject(5, butaca.tipo)
                setTimestamp(6, Timestamp.valueOf(timeStamp))
                setTimestamp(7, Timestamp.valueOf(timeStamp))
                setBoolean(8, false)
            }
            val rs = stmt.executeUpdate()
            if (rs > 0) {
                val generatedKeys = stmt.generatedKeys
                if (generatedKeys.next()) {
                    result = butaca.copy(
                        id = generatedKeys.getString(1),
                        createdAt = timeStamp,
                        updatedAt = timeStamp
                    )
                }
            }
        }
        return result
    }

    /**
     * Actualiza una butaca en la base de datos.
     */
    override fun update(id: String, butaca: Butaca): Butaca? {
        logger.debug { "Actualizando butaca por id: $id" }
        var result: Butaca = this.findById(id) ?: return null
        DataBaseManager.use { db ->
            val sql =
                "UPDATE butacas SET fila = ?, columna = ?, estado = ?, ocupacion = ?, tipo = ?, updated_at = ? WHERE id = ?"
            val stmt = db.prepareStatement(sql).apply {
                setString(1, butaca.fila)
                setString(2, butaca.columna)
                setObject(3, butaca.estado)
                setObject(4, butaca.ocupacion)
                setObject(5, butaca.tipo)
                setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()))
                setString(7, id)
            }
            val rs = stmt.executeUpdate()
            if (rs > 0) {
                result = butaca.copy(
                    id = id,
                    updatedAt = LocalDateTime.now()
                )
            }
        }
        return result
    }

    /**
     * Elimina una butaca de la base de datos.
     */
    override fun delete(id: String): Butaca? {
        logger.debug { "Borrando butaca por id: $id" }
        var result: Butaca = this.findById(id) ?: return null
        DataBaseManager.use { db ->
            val sql = "DELETE FROM butacas WHERE id = ?"
            val stmt = db.prepareStatement(sql).apply {
                setString(1, id)
            }
            val rs = stmt.executeUpdate()
            if (rs > 0) {
                result = result.copy(isDeleted = true)
            }
        }
        return result
    }
}

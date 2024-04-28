package org.example.socios.repositories

import org.example.database.service.SqlDeLightManager
import org.example.socios.models.Socio
import org.koin.core.annotation.Singleton
import org.lighthousegames.logging.logging
import java.time.LocalDateTime
/**
 * @author Alba Garcia
 */
private val logger = logging()


/**
 * Implementación de la interfaz SociosRepository que utiliza SQLDelight para acceder y manipular los datos de los socios.
 * @property dbManager el gestor de la base de datos SQLDelight
 */
@Singleton
class SociosRepositoryImpl(
    private val dbManager: SqlDeLightManager
) : SociosRepository {
    private val db = dbManager.databaseQueries

    /**
     * Obtiene todos los socios.
     * @return una lista que contiene todos los socios
     */
    override fun findAll(): List<Socio> {
        logger.debug { "Buscando todos los socios" }
        return db.selectAllSocios().executeAsList().map { it.toSocios() }
    }

    /**
     * Busca un socio por su identificador único.
     * @param id el identificador del socio a buscar
     * @return el socio encontrado o null si no se encuentra ningún socio con el identificador dado
     */
    override fun findById(id: Long): Socio? {
        logger.debug { "Buscando socios por id: $id" }
        return db.selectSociosById(id).executeAsOneOrNull()?.toSocios()
    }

    /**
     * Guarda un nuevo socio en el repositorio.
     * @param socio el socio a guardar
     * @return el socio guardado
     */
    override fun save(socio: Socio): Socio {
        logger.debug { "Guardando socio: $socio" }

        val timeStamp = LocalDateTime.now().toString()

        db.transaction {
            db.insertSocios(
                nombre = socio.nombre,
                created_at = timeStamp,
                updated_at = timeStamp,
            )
        }

        return db.selectSociosLastInserted().executeAsOne().toSocios()
    }

    /**
     * Actualiza un socio existente en el repositorio.
     * @param id el identificador del socio a actualizar
     * @param socio el nuevo estado del socio
     * @return el socio actualizado o null si no se encuentra ningún socio con el identificador dado
     */
    override fun update(id: Long, socio: Socio): Socio? {
        logger.debug { "Actualizando socio por id: $id" }
        var result = this.findById(id) ?: return null
        val timeStamp = LocalDateTime.now()
        result = result.copy(
            nombre = socio.nombre,
            isDeleted = socio.isDeleted,
            updatedAt = timeStamp
        )

        db.updateSocios(
            nombre = result.nombre,
            updated_at = timeStamp.toString(),
            is_deleted = if (result.isDeleted) 1 else 0,
            id = id,
        )
        return result
    }

    /**
     * Elimina un socio del repositorio.
     * @param id el identificador del socio a eliminar
     * @return el socio eliminado o null si no se encuentra ningún socio con el identificador dado
     */
    override fun delete(id: Long): Socio? {
        logger.debug { "Borrando socio por id: $id" }
        val result = this.findById(id) ?: return null
        val timeStamp = LocalDateTime.now()
        db.updateSocios(
            nombre = result.nombre,
            is_deleted = 1,
            updated_at = timeStamp.toString(),
            id = result.id,
        )
        return result.copy(isDeleted = true, updatedAt = timeStamp)
    }

}
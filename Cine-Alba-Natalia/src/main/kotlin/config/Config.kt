package org.example.config

import org.lighthousegames.logging.logging
import java.nio.file.Files
import java.util.*
import kotlin.io.path.Path

/**
 * @author Alba Garcia
 */
private val logger = logging()
/**
 * Objeto singleton que almacena la configuración de la aplicación.
 */
object Config {
    /** URL de la base de datos */
    var databaseUrl: String = "jdbc:sqlite:cine.db"
        private set

    /** Indica si se deben inicializar las tablas de la base de datos al iniciar la aplicación */
    var databaseInitTables: Boolean = false
        private set

    /** Indica si se deben inicializar los datos de la base de datos al iniciar la aplicación */
    var databaseInitData: Boolean = false
        private set

    /** Ruta donde se almacenan los datos */
    var storageData: String = "database/data"
        private set

    /** Tamaño máximo de la caché */
    var cacheSize: Int = 10
        private set

    init {
        try {
            logger.debug { "Cargando configuración" }
            val properties = Properties()
            properties.load(ClassLoader.getSystemResourceAsStream("config.properties"))
            databaseUrl = properties.getProperty("database.url", this.databaseUrl)
            databaseInitTables =
                properties.getProperty("database.init.tables", this.databaseInitTables.toString()).toBoolean()
            databaseInitData =
                properties.getProperty("database.init.data", this.databaseInitData.toString()).toBoolean()
            storageData = properties.getProperty("storage.data", this.storageData)
            cacheSize = properties.getProperty("cache.size", this.cacheSize.toString()).toInt()
            logger.debug { "Configuración cargada correctamente" }

            Files.createDirectories(Path(storageData))

        } catch (e: Exception) {
            logger.error { "Error cargando configuración: ${e.message}" }
            logger.error { "Usando valores por defecto" }
        }
    }
}
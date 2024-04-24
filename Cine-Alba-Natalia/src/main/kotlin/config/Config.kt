package org.example.config

import org.lighthousegames.logging.logging
import java.nio.file.Files
import java.util.*
import kotlin.io.path.Path

/**
 * Objeto singleton que maneja la configuración de la aplicación.
 *
 * Esta clase carga la configuración desde un archivo properties y proporciona acceso a las diferentes propiedades de configuración.
 *
 * @author Natalia Gonzalez
 * @since 1.0
 */
private val logger = logging()

object Config {

    /**
     * URL de la base de datos.
     */
    var databaseUrl: String = "jdbc:sqlite:cine.db"
        private set

    /**
     * Indica si se deben inicializar las tablas de la base de datos al inicio.
     */
    var databaseInitTables: Boolean = false
        private set

    /**
     * Indica si se debe inicializar los datos de la base de datos al inicio.
     */
    var databaseInitData: Boolean = false
        private set

    /**
     * Directorio de almacenamiento de datos.
     */
    var storageData: String = "database/data"
        private set

    /**
     * Tamaño de la caché.
     */
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

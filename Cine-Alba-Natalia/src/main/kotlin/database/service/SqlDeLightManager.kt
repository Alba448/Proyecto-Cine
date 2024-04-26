package org.example.database.service

import org.example.database.data.initDemoProductos
import org.example.database.data.initDemoSocios
import org.koin.core.annotation.Property
import org.koin.core.annotation.Singleton
import org.lighthousegames.logging.logging

/**
 * @author Alba Garcia
 */
private val logger = logging()

/**
 * Gestor de bases de datos SQLDelight que proporciona métodos para inicializar y gestionar la base de datos.
 * @property _databaseUrl la URL de la base de datos (por defecto: "jdbc:sqlite:productos.db")
 * @property _databaseInitData indica si se deben inicializar los datos de la base de datos al iniciar la aplicación (por defecto: "true")
 * @property _databaseInMemory indica si se debe utilizar una base de datos en memoria (por defecto: "true")
 */
@Singleton
class SqlDeLightManager(
    @Property("database.url")
    private val _databaseUrl: String = "jdbc:sqlite:productos.db",
    @Property("database.init.data")
    private val _databaseInitData: String = "true",
    @Property("database.inmemory")
    private val _databaseInMemory: String = "true"
) {
    private val databaseUrl: String = _databaseUrl
    private val databaseInitData: Boolean = _databaseInitData.toBoolean()
    private val databaseInMemory: Boolean = _databaseInMemory.toBoolean()
    val databaseQueries: DatabaseQueries = initQueries()

    init {
        logger.debug { "Inicializando el gestor de Bases de Datos con SQLDelight" }
        initialize()
    }

    /**
     * Inicializa la base de datos y carga datos de ejemplo si está habilitado.
     */
    private fun initialize() {
        if (databaseInitData) {
            removeAllData()
            initDataExamples()
        }
    }

    /**
     * Inicializa y devuelve las consultas de la base de datos.
     * @return las consultas de la base de datos inicializada
     */
    private fun initQueries(): DatabaseQueries {
        return if (databaseInMemory) {
            logger.debug { "SqlDeLightClient - InMemory" }
            JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        } else {
            logger.debug { "SqlDeLightClient - File: ${databaseUrl}" }
            JdbcSqliteDriver(databaseUrl)
        }.let { driver ->
            logger.debug { "Creando Tablas (si es necesario)" }
            AppDatabase.Schema.create(driver)
            AppDatabase(driver)
        }.databaseQueries
    }

    /**
     * Inicializa los datos de ejemplo en la base de datos.
     */
    private fun initDataExamples() {
        logger.debug { "Iniciando datos de ejemplo" }
        databaseQueries.transaction {
            demoProductos()
            demoSocios()
        }
    }

    /**
     * Inserta datos de ejemplo de productos en la base de datos.
     */
    private fun demoProductos() {
        logger.debug { "Datos de ejemplo de Productos" }
        initDemoProductos().forEach {
            databaseQueries.insertProducto(
                nombre = it.nombre,
                precio = it.precio,
                stock = it.stock.toLong(),
                created_at = it.createdAt.toString(),
                updated_at = it.updatedAt.toString(),
            )
        }
    }

    /**
     * Inserta datos de ejemplo de socios en la base de datos.
     */
    private fun demoSocios() {
        logger.debug { "Datos de ejemplo de Socios" }
        initDemoSocios().forEach {
            databaseQueries.insertCliente(
                nombre = it.nombre,
                created_at = it.createdAt.toString(),
                updated_at = it.updatedAt.toString(),
            )
        }
    }

    /**
     * Elimina todos los datos de la base de datos.
     */
    private fun removeAllData() {
        logger.debug { "SqlDeLightClient.removeAllData()" }
        databaseQueries.transaction {
            databaseQueries.removeAllVentas()
            databaseQueries.removeAllSocios()
            databaseQueries.removeAllProductos()
        }
    }
}
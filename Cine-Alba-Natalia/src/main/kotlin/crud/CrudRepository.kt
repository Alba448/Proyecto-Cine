package org.example.crud

/**
 * Interfaz genérica para operaciones básicas de CRUD (Crear, Leer, Actualizar, Eliminar) en una base de datos.
 *
 * Esta interfaz define métodos para realizar operaciones de CRUD en una entidad específica.
 *
 * @param T el tipo de la entidad que se manipula en la base de datos.
 * @param ID el tipo del identificador único de la entidad.
 * @author Natalia Gonzalez
 * @since 1.0
 */
interface CrudRepository<T, ID> {
    /**
     * Recupera todas las entidades almacenadas en la base de datos.
     *
     * @return una lista de todas las entidades.
     */
    fun findAll(): List<T>

    /**
     * Busca una entidad por su identificador único.
     *
     * @param id el identificador único de la entidad.
     * @return la entidad encontrada o null si no se encuentra.
     */
    fun findById(id: ID): T?

    /**
     * Guarda una nueva entidad en la base de datos o actualiza una entidad existente.
     *
     * @param item la entidad a guardar o actualizar.
     * @return la entidad guardada o actualizada.
     */
    fun save(item: T): T

    /**
     * Actualiza una entidad existente en la base de datos.
     *
     * @param id el identificador único de la entidad a actualizar.
     * @param item la nueva versión de la entidad.
     * @return la entidad actualizada o null si no se encuentra la entidad con el identificador dado.
     */
    fun update(id: ID, item: T): T?

    /**
     * Elimina una entidad de la base de datos.
     *
     * @param id el identificador único de la entidad a eliminar.
     * @return la entidad eliminada o null si no se encuentra la entidad con el identificador dado.
     */
    fun delete(id: ID): T?
}

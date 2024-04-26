package org.example.socios.repositories

import org.example.socios.models.Socio
/**
 * @author Alba Garcia
 */
/**
 * Interfaz que define las operaciones disponibles para acceder y manipular los datos de los socios.
 */
interface SociosRepository {
    /**
     * Obtiene todos los socios.
     * @return una lista que contiene todos los socios
     */
    fun findAll(): List<Socio>

    /**
     * Busca un socio por su identificador único.
     * @param id el identificador del socio a buscar
     * @return el socio encontrado o null si no se encuentra ningún socio con el identificador dado
     */
    fun findById(id: Long): Socio?

    /**
     * Guarda un nuevo socio en el repositorio.
     * @param socio el socio a guardar
     * @return el socio guardado
     */
    fun save(socio: Socio): Socio

    /**
     * Actualiza un socio existente en el repositorio.
     * @param id el identificador del socio a actualizar
     * @param socio el nuevo estado del socio
     * @return el socio actualizado o null si no se encuentra ningún socio con el identificador dado
     */
    fun update(id: Long, socio: Socio): Socio?

    /**
     * Elimina un socio del repositorio.
     * @param id el identificador del socio a eliminar
     * @return el socio eliminado o null si no se encuentra ningún socio con el identificador dado
     */
    fun delete(id: Long): Socio?
}
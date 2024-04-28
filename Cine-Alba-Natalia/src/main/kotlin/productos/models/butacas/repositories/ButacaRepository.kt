package org.example.productos.models.butacas.repositories

import org.example.crud.CrudRepository
import org.example.productos.models.butacas.models.Butaca
import org.example.productos.models.butacas.models.Estado
import org.example.productos.models.butacas.models.Ocupacion
import org.example.productos.models.butacas.models.Tipo

/**
 * Interfaz que define las operaciones de persistencia para las butacas.
 *
 * @author Natalia Gonzalez
 * @since 1.0
 */
interface ButacaRepository : CrudRepository<Butaca, String> {
    fun reservarButaca(butaca: Butaca) : Butaca?

    fun devolverButaca(butaca: Butaca) : Butaca?

    /**
     * Encuentra todas las butacas.
     */
    override fun findAll(): List<Butaca>

    /**
     * Encuentra una butaca por su ID.
     */
    override fun findById(id: String): Butaca?

    /**
     * Encuentra butacas por tipo.
     */
    fun findByTipo(tipo: Tipo): List<Butaca>

    /**
     * Encuentra butacas por estado.
     */
    fun findByEstado(estado: Estado): List<Butaca>

    /**
     * Encuentra butacas por ocupación.
     */
    fun findByOcupacion(ocupacion: Ocupacion): List<Butaca>

    /**
     * Guarda una butaca.
     */
    override fun save(butaca: Butaca): Butaca

    /**
     * Actualiza una butaca.
     */
    override fun update(id: String, butaca: Butaca): Butaca?

    /**
     * Elimina una butaca.
     */
    override fun delete(id: String): Butaca?
}

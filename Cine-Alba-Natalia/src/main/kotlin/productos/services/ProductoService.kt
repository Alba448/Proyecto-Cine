package org.example.productos.services

import com.github.michaelbull.result.Result
import org.example.productos.errors.ProductoError
import org.example.productos.models.Producto
import org.example.productos.models.butacas.errors.ButacaError
import org.example.productos.models.butacas.models.Butaca
import org.example.productos.models.butacas.models.Estado
import org.example.productos.models.butacas.models.Ocupacion
import org.example.productos.models.butacas.models.Tipo
import org.example.productos.models.complementos.errors.ComplementoError
import org.example.productos.models.complementos.models.Bebida
import org.example.productos.models.complementos.models.Comida
import org.example.productos.models.complementos.models.Complemento
import java.io.File

/**
 * Servicio para gestionar productos, butacas y complementos.
 *
 * Este servicio proporciona métodos para realizar operaciones relacionadas con productos, butacas y complementos.
 *
 * @since 1.0
 * @author Natalia Gonzalez
 */
interface ProductoService {
    /**
     * Obtiene todos los productos.
     */
    fun getAll(): Result<List<Producto>, ProductoError>

    /**
     * Obtiene todas las butacas.
     */
    fun getAllButacas(): Result<List<Butaca>, ButacaError>

    /**
     * Obtiene todos los complementos.
     */
    fun getAllComplementos(): Result<List<Complemento>, ComplementoError>

    /**
     * Obtiene un producto por su ID.
     */
    fun getById(id: Long): Result<Producto?, ProductoError>

    /**
     * Obtiene una butaca por su ID.
     */
    fun getButacaById(id: Long): Result<Butaca, ButacaError>

    /**
     * Obtiene butacas por su tipo.
     */
    fun getButacaByTipo(tipo: Tipo): Result<List<Butaca>, ButacaError>

    /**
     * Obtiene butacas por su estado.
     */
    fun getButacaByEstado(estado: Estado): Result<List<Butaca>, ButacaError>

    /**
     * Obtiene butacas por su ocupación.
     */
    fun getButacaByOcupacion(ocupacion: Ocupacion): Result<List<Butaca>, ButacaError>

    /**
     * Obtiene un complemento por su ID.
     */
    fun getComplementoById(id: Long): Result<Complemento, ComplementoError>

    /**
     * Obtiene complementos por su bebida.
     */
    fun getComplementoByBebida(bebida: Bebida): Result<List<Complemento>, ComplementoError>

    /**
     * Obtiene complementos por su comida.
     */
    fun getComplementoByComida(comida: Comida): Result<List<Complemento>, ComplementoError>

    /**
     * Obtiene complementos por su stock.
     */
    fun getComplementoByStock(stock: Int): Result<List<Complemento>, ComplementoError>

    /**
     * Crea un nuevo producto.
     */
    fun create(producto: Producto): Result<Producto, ProductoError>

    /**
     * Actualiza un producto existente.
     */
    fun update(id: Long, producto: Producto): Result<Producto, ProductoError>

    /**
     * Elimina un producto.
     */
    fun delete(id: Long): Result<Producto, ProductoError>

    /**
     * Importa los complementos de un fichero csv.
     */
    fun import(csvFile: File):Result<Unit, ComplementoError>
}

package org.example.productos.services

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.andThen
import org.example.productos.errors.ProductoError
import org.example.productos.models.Producto
import org.example.productos.models.butacas.errors.ButacaError
import org.example.productos.models.butacas.models.Butaca
import org.example.productos.models.butacas.models.Estado
import org.example.productos.models.butacas.models.Ocupacion
import org.example.productos.models.butacas.models.Tipo
import org.example.productos.models.butacas.repositories.ButacaRepository
import org.example.productos.models.complementos.errors.ComplementoError
import org.example.productos.models.complementos.models.Bebida
import org.example.productos.models.complementos.models.Comida
import org.example.productos.models.complementos.models.Complemento
import org.example.productos.models.complementos.repositories.ComplementoRepository
import org.example.productos.models.complementos.storage.StorageComplemento
import org.example.productos.repositories.ProductoRepository
import org.example.productos.validators.ProductoValidator
import org.lighthousegames.logging.logging
import java.io.File

private val logger = logging()

/**
 * Implementación del servicio de productos.
 *
 * Este servicio proporciona métodos para gestionar productos, butacas y complementos.
 *
 * @since 1.0
 * @author Natalia Gonzalez
 */
class ProductoServiceImpl(
    private val productoRepository: ProductoRepository,
    private val butacaRepository: ButacaRepository,
    private val complementoRepository: ComplementoRepository,
    private val productoValidator: ProductoValidator,
    private val complementoStorage: StorageComplemento
) : ProductoService {
    /**
     * Obtiene todos los productos.
     *
     * @return [Result] que contiene una lista de productos o un error si la operación falla.
     */
    override fun getAll(): Result<List<Producto>, ProductoError> {
        logger.debug { "Obteniendo todos los productos" }
        return Ok(productoRepository.findAll())
    }

    /**
     * Obtiene todas las butacas.
     *
     * @return [Result] que contiene una lista de butacas o un error si la operación falla.
     */
    override fun getAllButacas(): Result<List<Butaca>, ButacaError> {
        logger.debug { "Obteniendo todas las butacas" }
        return Ok(butacaRepository.findAll())
    }

    /**
     * Obtiene todos los complementos.
     *
     * @return [Result] que contiene una lista de complementos o un error si la operación falla.
     */
    override fun getAllComplementos(): Result<List<Complemento>, ComplementoError> {
        logger.debug { "Obteniendo todos los complementos" }
        return Ok(complementoRepository.findAll())
    }

    /**
     * Obtiene un producto por su id.
     *
     * @return [Result] que contiene un producto o un error si la operación falla.
     */
    override fun getById(id: Long): Result<Producto?, ProductoError> {
        logger.debug { "Obteniendo producto por id: $id" }
        return Ok(productoRepository.findById(id))
    }

    /**
     * Obtiene una butaca por su id.
     *
     * @return [Result] que contiene una butaca o un error si la operación falla.
     */
    override fun getButacaById(id: Long): Result<Butaca, ButacaError> {
        logger.debug { "Obteniendo butaca por id: $id" }
        return butacaRepository.findById(id.toString())
            ?.let { Ok(it) }
            ?: Err(ButacaError.ButacaNoEncontrada("Butaca no encontrada con id: $id"))
    }

    /**
     * Obtiene una butaca por su tipo.
     *
     * @return [Result] que contiene una lista de butacas o un error si la operación falla.
     */
    override fun getButacaByTipo(tipo: Tipo): Result<List<Butaca>, ButacaError> {
        logger.debug { "Obteniendo butacas por tipo: $tipo" }
        return Ok(butacaRepository.findByTipo(tipo))
    }

    /**
     * Obtiene una butaca por su estado.
     *
     * @return [Result] que contiene una lista de butacas o un error si la operación falla.
     */
    override fun getButacaByEstado(estado: Estado): Result<List<Butaca>, ButacaError> {
        logger.debug { "Obteniendo butacas por estado: $estado" }
        return Ok(butacaRepository.findByEstado(estado))
    }

    /**
     * Obtiene una butaca por su ocupación.
     *
     * @return [Result] que contiene una lista de butacas o un error si la operación falla.
     */
    override fun getButacaByOcupacion(ocupacion: Ocupacion): Result<List<Butaca>, ButacaError> {
        logger.debug { "Obteniendo butacas por ocupacion: $ocupacion" }
        return Ok(butacaRepository.findByOcupacion(ocupacion))
    }

    /**
     * Obtiene un complemento por su id.
     *
     * @return [Result] que contiene un complemento o un error si la operación falla.
     */
    override fun getComplementoById(id: Long): Result<Complemento, ComplementoError> {
        logger.debug { "Obteniendo complemento por id: $id" }
        return complementoRepository.findById(id)
            ?.let { Ok(it) }
            ?: Err(ComplementoError.ComplementoNoEncontrado("Complemento no encontrado con id: $id"))
    }

    /**
     * Obtiene un complemento por su bebida.
     *
     * @return [Result] que contiene una lista de complementos o un error si la operación falla.
     */
    override fun getComplementoByBebida(bebida: Bebida): Result<List<Complemento>, ComplementoError> {
        logger.debug { "Obteniendo complemento por bebida: $bebida" }
        return Ok(complementoRepository.findByBebida(bebida))
    }

    /**
     * Obtiene un complemento por su comida.
     *
     * @return [Result] que contiene una lista de complementos o un error si la operación falla.
     */
    override fun getComplementoByComida(comida: Comida): Result<List<Complemento>, ComplementoError> {
        logger.debug { "Obteniendo complemento por comida: $comida" }
        return Ok(complementoRepository.findByComida(comida))
    }

    /**
     * Obtiene un complemento por su stock.
     *
     * @return [Result] que contiene una lista de complementos o un error si la operación falla.
     */
    override fun getComplementoByStock(stock: Int): Result<List<Complemento>, ComplementoError> {
        logger.debug { "Obteniendo complemento por stock: $stock" }
        return Ok(complementoRepository.findByStock(stock))
    }

    /**
     * Crea un producto.
     *
     * @return [Result] que contiene una lista de productos o un error si la operación falla.
     */
    override fun create(producto: Producto): Result<Producto, ProductoError> {
        logger.debug { "Guardando producto: $producto" }
        return productoValidator.validate(producto).andThen {
            Ok(productoRepository.save(it))
        }
    }

    /**
     * Actualiza un producto.
     *
     * @return [Result] que contiene un producto o un error si la operación falla.
     */
    override fun update(id: Long, producto: Producto): Result<Producto, ProductoError> {
        logger.debug { "Actualizando producto por id: $id" }
        return productoValidator.validate(producto).andThen { p ->
            productoRepository.update(id, p)
                ?.let { Ok(it) }
                ?: Err(ProductoError.ProductoNoActualizado("Producto no actualizado con id: $id"))
        }
    }

    /**
     * Elimina un producto.
     *
     * @return [Result] que contiene un producto o un error si la operación falla.
     */
    override fun delete(id: Long): Result<Producto, ProductoError> {
        logger.debug { "Borrando producto por id: $id" }
        return productoRepository.delete(id)
            ?.let { Ok(it) }
            ?: Err(ProductoError.ProductoNoEliminado("Producto no eliminado con id: $id"))
    }

    /**
     * Importa un fichero csv.
     *
     * @return [Result] que contiene una unidad o un error si la operación falla.
     */
    override fun import(csvFile: File): Result<Unit, ComplementoError> {
        return complementoStorage.load(csvFile).andThen { complementos ->
            complementos.forEach { c ->
                complementoRepository.save(c)
            }
            Ok(Unit)
        }
    }
}
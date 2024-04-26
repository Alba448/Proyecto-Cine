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
import org.example.productos.models.butacas.repositories.ButacaRepositoryImpl
import org.example.productos.models.complementos.errors.ComplementoError
import org.example.productos.models.complementos.models.Bebida
import org.example.productos.models.complementos.models.Comida
import org.example.productos.models.complementos.models.Complemento
import org.example.productos.models.complementos.repositories.ComplementoRepositoryImpl
import org.example.productos.repositories.ProductoRepositoryImpl
import org.example.productos.validators.ProductoValidator
import org.lighthousegames.logging.logging

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
    private val productoRepositoryImpl: ProductoRepositoryImpl,
    private val butacaRepositoryImpl: ButacaRepositoryImpl,
    private val complementoRepositoryImpl: ComplementoRepositoryImpl,
    private val productoValidator: ProductoValidator
) : ProductoService {
    override fun getAll(): Result<List<Producto>, ProductoError> {
        logger.debug { "Obteniendo todos los productos" }
        return Ok(productoRepositoryImpl.findAll())
    }

    override fun getAllButacas(): Result<List<Butaca>, ButacaError> {
        logger.debug { "Obteniendo todas las butacas" }
        return Ok(butacaRepositoryImpl.findAll())
    }

    override fun getAllComplementos(): Result<List<Complemento>, ComplementoError> {
        logger.debug { "Obteniendo todos los complementos" }
        return Ok(complementoRepositoryImpl.findAll())
    }

    override fun getById(id: Long): Result<Producto?, ProductoError> {
        logger.debug { "Obteniendo producto por id: $id" }
        return Ok(productoRepositoryImpl.findById(id))
    }

    override fun getButacaById(id: Long): Result<Butaca, ButacaError> {
        logger.debug { "Obteniendo butaca por id: $id" }
        return butacaRepositoryImpl.findById(id.toString())
            ?.let { Ok(it) }
            ?: Err(ButacaError.ButacaNoEncontrada("Butaca no encontrada con id: $id"))
    }

    override fun getButacaByTipo(tipo: Tipo): Result<List<Butaca>, ButacaError> {
        logger.debug { "Obteniendo butacas por tipo: $tipo" }
        return Ok(butacaRepositoryImpl.findByTipo(tipo))
    }

    override fun getButacaByEstado(estado: Estado): Result<List<Butaca>, ButacaError> {
        logger.debug { "Obteniendo butacas por estado: $estado" }
        return Ok(butacaRepositoryImpl.findByEstado(estado))
    }

    override fun getButacaByOcupacion(ocupacion: Ocupacion): Result<List<Butaca>, ButacaError> {
        logger.debug { "Obteniendo butacas por ocupacion: $ocupacion" }
        return Ok(butacaRepositoryImpl.findByOcupacion(ocupacion))
    }

    override fun getComplementoById(id: Long): Result<Complemento, ComplementoError> {
        logger.debug { "Obteniendo complemento por id: $id" }
        return complementoRepositoryImpl.findById(id)
            ?.let { Ok(it) }
            ?: Err(ComplementoError.ComplementoNoEncontrado("Complemento no encontrado con id: $id"))
    }

    override fun getComplementoByBebida(bebida: Bebida): Result<List<Complemento>, ComplementoError> {
        logger.debug { "Obteniendo complemento por bebida: $bebida" }
        return Ok(complementoRepositoryImpl.findByBebida(bebida))
    }

    override fun getComplementoByComida(comida: Comida): Result<List<Complemento>, ComplementoError> {
        logger.debug { "Obteniendo complemento por comida: $comida" }
        return Ok(complementoRepositoryImpl.findByComida(comida))
    }

    override fun getComplementoByStock(stock: Int): Result<List<Complemento>, ComplementoError> {
        logger.debug { "Obteniendo complemento por stock: $stock" }
        return Ok(complementoRepositoryImpl.findByStock(stock))
    }

    override fun create(producto: Producto): Result<Producto, ProductoError> {
        logger.debug { "Guardando producto: $producto" }
        return productoValidator.validate(producto).andThen {
            Ok(productoRepositoryImpl.save(it))
        }
    }

    override fun update(id: Long, producto: Producto): Result<Producto, ProductoError> {
        logger.debug { "Actualizando producto por id: $id" }
        return productoValidator.validate(producto).andThen { p ->
            productoRepositoryImpl.update(id, p)
                ?.let { Ok(it) }
                ?: Err(ProductoError.ProductoNoActualizado("Producto no actualizado con id: $id"))
        }
    }

    override fun delete(id: Long): Result<Producto, ProductoError> {
        logger.debug { "Borrando producto por id: $id" }
        return productoRepositoryImpl.delete(id)
            ?.let { Ok(it) }
            ?: Err(ProductoError.ProductoNoEliminado("Producto no eliminado con id: $id"))
    }
}
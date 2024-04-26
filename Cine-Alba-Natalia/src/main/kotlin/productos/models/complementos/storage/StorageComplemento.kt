package org.example.productos.models.complementos.storage

import com.github.michaelbull.result.Result
import org.example.productos.models.complementos.errors.ComplementoError
import org.example.productos.models.complementos.models.Complemento
import java.io.File

/**
 * Interfaz que define las operaciones de almacenamiento de complementos.
 *
 * @since 1.0
 * @author Natalia Gonzalez
 */
interface StorageComplemento {
    /**
     * Carga los complementos desde un archivo.
     *
     * @param file El archivo desde el cual cargar los complementos.
     * @return [Result] que contiene una lista de complementos cargados o un error si la carga falla.
     */
    fun load(file : File) : Result<List<Complemento>, ComplementoError>
}
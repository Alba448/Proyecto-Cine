package org.example.sala.services

import com.github.michaelbull.result.Result
import org.example.productos.models.butacas.errors.ButacaError
import org.example.productos.models.butacas.models.Butaca

/**
 * Interfaz que define los servicios disponibles para gestionar una sala de cine.
 *
 * Esta interfaz proporciona métodos para simular eventos en la sala, comprar y devolver entradas, ver el estado del cine,
 * obtener la recaudación, importar complementos, exportar la configuración del cine, y configurar y actualizar las butacas.
 *
 * @author Natalia Gonzalez
 * @since 1.0
 */
interface SalaService {
    /**
     * Simula eventos en la sala de cine.
     */
    fun simular()

    /**
     * Permite comprar una entrada para una butaca.
     */
    fun comprarEntrada() : Result<Butaca, ButacaError>

    /**
     * Permite devolver una entrada previamente comprada.
     */
    fun devolverEntrada() : Result<Butaca, ButacaError>

    /**
     * Muestra el estado actual del cine y las butacas.
     */
    fun verEstadoCine()

    /**
     * Obtiene la recaudación actual del cine.
     */
    fun obtenerRecaudacion()

    /**
     * Importa complementos o configuraciones adicionales para la sala de cine.
     */
    fun importarComplementos()

    /**
     * Exporta la configuración actual de la sala de cine.
     */
    fun exportarCine()

    /**
     * Configura una butaca específica en la sala de cine.
     */
    fun configurarButaca()

    /**
     * Actualiza la información de una butaca en la sala de cine.
     */
    fun actualizarButaca()

    /**
     * Se acaba el programa
     */
    fun salir()
}

package org.example.sala.services

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.productos.models.butacas.errors.ButacaError
import org.example.productos.models.butacas.models.Butaca
import org.example.productos.models.butacas.repositories.ButacaRepository
import org.example.productos.models.butacas.repositories.ButacaRepositoryImpl
import org.example.productos.services.ProductoService
import org.example.sala.models.Sala
import org.lighthousegames.logging.logging
import java.io.File

private val logger = logging()

/**
 * Implementación concreta de SalaService.
 *
 * Esta implementación proporciona métodos para interactuar con una sala de cine, como comprar y devolver entradas,
 * ver el estado del cine, obtener la recaudación, importar y exportar configuraciones, y configurar y actualizar butacas.
 *
 * @property sala Instancia de Sala que representa la sala de cine con la que se va a interactuar.
 * @constructor Crea una instancia de SalaServiceImpl con una instancia de Sala proporcionada opcionalmente.
 * @param sala Instancia de Sala que representa la sala de cine. Por defecto, se crea una nueva instancia de Sala.
 * @since 1.0
 * @author Natalia Gonzalez
 */
class SalaServiceImpl(
    private val sala: Sala = Sala(),
    private val butacaRepository: ButacaRepository,
    private val productoService: ProductoService
) : SalaService {

    /**
     * Permite al usuario comprar una entrada para una butaca.
     */
    override fun comprarEntrada(): Result<Butaca, ButacaError> {
        return butacaRepository.reservarButaca(butaca = Butaca())
            ?.let { Ok(it) }
            ?: Err(ButacaError.ButacaNoReservada("Butaca no reservada con id: ${Butaca().id}"))
    }

    /**
     * Permite al usuario devolver una entrada previamente comprada.
     */
    override fun devolverEntrada(): Result<Butaca, ButacaError> {
        return butacaRepository.devolverButaca(butaca = Butaca())
            ?.let { Ok(it) }
            ?: Err(ButacaError.ButacaNoReservada("Butaca no devuelta con id: ${Butaca().id}"))
    }

    /**
     * Muestra el estado actual del cine y las butacas.
     */
    override fun verEstadoCine() {
        logger.debug { "Imprimiendo sala..." }
        sala.inicializarSala()
        sala.imprimirSala()
    }

    /**
     * Obtiene la recaudación actual del cine.
     */
    override fun obtenerRecaudacion() {
    }

    /**
     * Importa complementos o configuraciones adicionales para la sala de cine.
     */
    override fun importarComplementos() {
        val csvFile = File("data", "complementos.csv")
        productoService.import(csvFile)
    }

    /**
     * Exporta la configuración actual de la sala de cine.
     */
    override fun exportarCine() {
    }

    /**
     * Configura una butaca específica en la sala de cine.
     */
    override fun configurarButaca() {
    }

    /**
     * Actualiza la información de una butaca en la sala de cine.
     */
    override fun actualizarButaca() {
    }

    /**
     * Se acaba el programa
     */
    override fun salir() {
        println("Adiós")
    }

    /**
     * Método que inicia la simulación de eventos en la sala de cine.
     */
    override fun simular() {
        do {
            println("¡Bienvenido al cine!")
            println("¿Qué desea hacer?")
            println("1- Comprar entrada")
            println("2- Devolver entrada")
            println("3- Ver estado del cine")
            println("4- Obtener recaudación")
            println("5- Importar complementos")
            println("6- Exportar cine")
            println("7- Configurar butaca")
            println("8- Actualizar butaca")
            println("0- Salir")

            val respuesta = readln()

            when (respuesta) {
                "1" -> comprarEntrada()
                "2" -> devolverEntrada()
                "3" -> verEstadoCine()
                "4" -> obtenerRecaudacion()
                "5" -> importarComplementos()
                "6" -> exportarCine()
                "7" -> configurarButaca()
                "8" -> actualizarButaca()
                "0" -> salir()
                else -> println("Introduzca un valor válido")
            }
        }while (respuesta != "0")
    }
}

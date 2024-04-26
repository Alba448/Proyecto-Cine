package org.example.sala.models

import org.example.productos.models.butacas.models.Butaca
import org.example.productos.models.butacas.models.Estado
import org.example.productos.models.butacas.models.Ocupacion
import org.example.productos.models.butacas.models.Tipo

private const val TAM_FILAS = 5
private const val TAM_COLUMNAS = 7

/**
 * Clase que representa una sala de cine.
 *
 * Esta clase modela la disposición de las butacas en una sala de cine y proporciona métodos para interactuar con ella.
 *
 * @property sala Matriz que representa la disposición de las butacas en la sala.
 * @constructor Crea una instancia de Sala y inicializa la matriz de butacas.
 * @since 1.0
 * @author Natalia Gonzalez
 */
class Sala {
    private val sala: Array<Array<Any?>> = Array(TAM_FILAS) { arrayOfNulls(TAM_COLUMNAS) }

    /**
     * Inicializa la sala con butacas aleatorias.
     *
     * @return La matriz que representa la disposición de las butacas en la sala.
     */
    fun inicializarSala() : Array<Array<Any?>>{
        repeat(35){
            posicionAleatoria(Butaca())
        }
        return sala
    }

    /**
     * Coloca un elemento en una posición aleatoria de la sala.
     *
     * @param item Elemento a colocar en la sala.
     * @return La matriz que representa la disposición de las butacas en la sala después de colocar el elemento.
     */
    fun posicionAleatoria(item: Any?) : Array<Array<Any?>> {
        var columna : Int
        var fila : Int
        do {
            fila = (0 until TAM_FILAS).random()
            columna = (0 until TAM_COLUMNAS).random()
        } while (sala[fila][columna] != null)
        sala[fila][columna] = item
        return sala
    }

    /**
     * Imprime la disposición de las butacas en la sala.
     *
     * @return La matriz que representa la disposición de las butacas en la sala.
     */
    fun imprimirSala() : Array<Array<Any?>> {
        for (fila in sala.indices) {
            for (columna in sala[fila].indices) {
                if (sala[fila][columna] is Butaca) {
                    when ((sala[fila][columna] as Butaca).estado) {
                        Estado.FUERA_DE_SERVICIO -> print("[⚫]")
                        Estado.ACTIVA -> {
                            when {
                                Butaca().tipo == Tipo.VIP -> print("[🟣]")
                                Butaca().tipo == Tipo.NORMAL -> print("[🟢]")
                                Butaca().ocupacion == Ocupacion.OCUPADA -> print("[🔴]")
                                Butaca().ocupacion == Ocupacion.EN_RESERVA -> print("[🟡]")
                            }
                        }
                        Estado.EN_MANTENIMIENTO -> print("[🟠]")
                    }
                }
            }
            println()
        }
        println("⚫-> FUERA DE SERVICIO 🟢-> BUTACA NORMAL 🟣-> BUTACA VIP 🔴-> OCUPADA 🟡-> EN RESERVA 🟠-> EN MANTENIMIENTO")
        return sala
    }
}

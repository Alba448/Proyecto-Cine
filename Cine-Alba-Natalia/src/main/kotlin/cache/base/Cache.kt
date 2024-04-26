package org.example.cache.base

import com.github.michaelbull.result.Result
import org.example.cache.error.CacheError

/**
 * @author Alba Garcia
 */
/**
 * Una interfaz genérica de caché para almacenar pares clave-valor.
 * @param K el tipo de las claves en el caché
 * @param T el tipo de los valores en el caché
 */
interface Cache<K, T> {
    /**
     * Recupera el valor asociado con la clave dada del caché.
     * @param key la clave cuyo valor asociado se va a recuperar
     * @return un Resultado que contiene el valor recuperado o un CacheError si la clave no se encuentra
     */
    fun get(key: K): Result<T, CacheError>

    /**
     * Asocia el valor especificado con la clave especificada en el caché.
     * @param key la clave con la que se asociará el valor especificado
     * @param value el valor que se asociará con la clave especificada
     * @return un Resultado que contiene el valor que se colocó en el caché
     */
    fun put(key: K, value: T): Result<T, Nothing>

    /**
     * Elimina la entrada asociada con la clave especificada del caché.
     * @param key la clave cuya entrada se va a eliminar del caché
     * @return un Resultado que contiene el valor eliminado o un CacheError si la clave no se encuentra
     */
    fun remove(key: K): Result<T, CacheError>

    /**
     * Elimina todas las entradas del caché.
     * @return un Resultado que indica si se limpió el caché correctamente
     */
    fun clear(): Result<Unit, Nothing>
}
package org.example.cache.base

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import org.example.cache.error.CacheError
import org.lighthousegames.logging.logging
import com.github.michaelbull.result.Result

private val logger = logging()
/**
 * @author Alba Garcia
 */
/**
 * Implementación de la interfaz Cache que utiliza un mapa mutable para almacenar los elementos en caché.
 * @param K el tipo de las claves en el caché
 * @param T el tipo de los valores en el caché
 * @property size el tamaño máximo del caché
 */
open class CacheImpl<K, T>(
    private val size: Int
) : Cache<K, T> {
    private val cache = mutableMapOf<K, T>()

    /**
     * Recupera el valor asociado con la clave dada del caché.
     * @param key la clave cuyo valor asociado se va a recuperar
     * @return un Resultado que contiene el valor recuperado o un CacheError si la clave no se encuentra
     */
    override fun get(key: K): Result<T, CacheError> {
        logger.debug { "Obteniendo valor de la cache" }
        return if (cache.containsKey(key)) {
            Ok(cache.getValue(key))
        } else {
            Err(CacheError("No existe el valor en la cache"))
        }
    }

    /**
     * Asocia el valor especificado con la clave especificada en el caché.
     * Si el tamaño del caché excede el límite, se elimina el elemento más antiguo.
     * @param key la clave con la que se asociará el valor especificado
     * @param value el valor que se asociará con la clave especificada
     * @return un Resultado que contiene el valor que se colocó en el caché
     */
    override fun put(key: K, value: T): Result<T, Nothing> {
        logger.debug { "Guardando valor en la cache" }
        if (cache.size >= size && !cache.containsKey(key)) {
            logger.debug { "Eliminando valor de la cache" }
            cache.remove(cache.keys.first())
        }
        cache[key] = value
        return Ok(value)
    }

    /**
     * Elimina la entrada asociada con la clave especificada del caché.
     * @param key la clave cuya entrada se va a eliminar del caché
     * @return un Resultado que contiene el valor eliminado o un CacheError si la clave no se encuentra
     */
    override fun remove(key: K): Result<T, CacheError> {
        logger.debug { "Eliminando valor de la cache" }
        return if (cache.containsKey(key)) {
            Ok(cache.remove(key)!!)
        } else {
            Err(CacheError("No existe el valor en la cache"))
        }
    }

    /**
     * Elimina todas las entradas del caché.
     * @return un Resultado que indica si se limpió el caché correctamente
     */
    override fun clear(): Result<Unit, Nothing> {
        logger.debug { "Limpiando cache" }
        cache.clear()
        return Ok(Unit)
    }
}
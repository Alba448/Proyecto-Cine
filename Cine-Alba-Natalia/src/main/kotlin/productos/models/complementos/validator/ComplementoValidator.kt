package org.example.productos.models.complementos.validator

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.productos.models.complementos.errors.ComplementoError
import org.example.productos.models.complementos.models.Bebida
import org.example.productos.models.complementos.models.Comida
import org.example.productos.models.complementos.models.Complemento

/**
 * Clase encargada de validar los complementos.
 *
 * @since 1.0
 * @param complemento El complemento a validar.
 * @return [Result] que contiene el complemento validado o un error si la validación falla.
 * @author Natalia Gonzalez
 */
class ComplementoValidator {
    /**
     * Valida un complemento.
     *
     * @param complemento El complemento a validar.
     * @return [Result] que contiene el complemento validado o un error si la validación falla.
     */
    fun validate(complemento: Complemento): Result<Complemento, ComplementoError> {
        return when {
            complemento.bebida!=Bebida.AGUA && complemento.bebida!=Bebida.REFRESCO -> Err(ComplementoError.ComplementoNoValido("Bebida inválida"))
            complemento.comida!=Comida.PALOMITAS && complemento.comida!=Comida.PATATAS && complemento.comida!=Comida.FRUTOS_SECOS -> Err(ComplementoError.ComplementoNoValido("Comida inválida"))
            complemento.stock < 0 -> Err(ComplementoError.ComplementoNoValido("Stock no puede ser menor a 0"))
            else -> Ok(complemento)
        }
    }
}

package org.example.productos.models.complementos.validator

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.productos.models.complementos.errors.ComplementoError
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
    fun validate(complemento: Complemento): Result<Complemento, ComplementoError> {
        return when {
            complemento.nombre.isBlank -> Err(ComplementoError.ComplementoNoValido("Nombre no puede estar vacío"))
            complemento.precio <= 0 -> Err(ComplementoError.ComplementoNoValido("Precio no puede ser menor o igual a 0"))
            complemento.stock < 0 -> Err(ComplementoError.ComplementoNoValido("Stock no puede ser menor a 0"))
            else -> Ok(complemento)
        }
    }
}

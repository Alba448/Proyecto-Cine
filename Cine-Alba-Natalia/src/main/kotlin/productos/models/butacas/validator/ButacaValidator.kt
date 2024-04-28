package org.example.productos.models.butacas.validator

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.productos.models.butacas.errors.ButacaError
import org.example.productos.models.butacas.models.Butaca
import org.example.productos.models.butacas.models.Estado
import org.example.productos.models.butacas.models.Ocupacion
import org.example.productos.models.butacas.models.Tipo

/**
 * Clase encargada de validar una butaca.
 *
 * @author Natalia Gonzalez
 * @since 1.0
 */
class ButacaValidator {
    /**
     * Valida una butaca.
     */
    fun validate(butaca: Butaca): Result<Butaca, ButacaError> {
        return when {
            !butaca.id.toString().matches("^[A-E][1-7]$".toRegex()) -> Err(ButacaError.ButacaNoValida("Formato del id inválido"))
            butaca.fila.toInt() !in 1..5 -> Err(ButacaError.ButacaNoValida("Fila inválida"))
            butaca.columna.toInt() !in 1..7 -> Err(ButacaError.ButacaNoValida("Columna inválida"))
            butaca.tipo != Tipo.NORMAL && butaca.tipo != Tipo.VIP -> Err(ButacaError.ButacaNoValida("Tipo de la butaca inválido"))
            butaca.estado != Estado.ACTIVA && butaca.estado != Estado.EN_MANTENIMIENTO && butaca.estado != Estado.FUERA_DE_SERVICIO -> Err(ButacaError.ButacaNoValida("Estado de la butaca inválido"))
            butaca.ocupacion != Ocupacion.LIBRE && butaca.ocupacion != Ocupacion.EN_RESERVA && butaca.ocupacion != Ocupacion.OCUPADA -> Err(ButacaError.ButacaNoValida("La butaca está ocupada"))
            else -> Ok(butaca)
        }
    }
}

package org.example.productos.validators

import org.example.productos.models.butacas.models.Butaca
import org.example.productos.models.butacas.validator.ButacaValidator
import org.example.productos.models.complementos.models.Complemento
import org.example.productos.models.complementos.validator.ComplementoValidator

/**
 * Validador para productos.
 *
 * Este validador combina validaciones para butacas y complementos.
 *
 * @since 1.0
 */
class ProductoValidator {
    /**
     * Valida una butaca y un complemento.
     *
     * @param butaca La butaca a validar.
     * @param complemento El complemento a validar.
     */
    fun validate(butaca: Butaca, complemento: Complemento) {
        ButacaValidator().validate(butaca)
        ComplementoValidator().validate(complemento)
    }
}

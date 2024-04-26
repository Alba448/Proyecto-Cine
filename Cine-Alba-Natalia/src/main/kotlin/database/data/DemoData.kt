package org.example.database.data

import org.example.productos.models.butacas.models.Butaca
import org.example.productos.models.complementos.models.Complemento
import org.example.socios.models.Socio

/**
 * @author Alba Garcia
 * @author Natalia Gonzalez
 */
/**
 * Inicializa una lista de productos de demostración.
 * @return una lista que contiene objetos de tipo Producto (Butaca y Complemento)
 */
fun initDemoProductos() = listOf(
    Butaca(),
    Complemento(),
    Butaca(),
    Complemento(),
    Butaca(),
    Complemento()
)

/**
 * Inicializa una lista de socios de demostración.
 * @return una lista que contiene un objeto de tipo Socio
 */
fun initDemoSocios() = listOf(
    Socio(
        id = 1,
        nombre = "Juan Pérez",
    )
)
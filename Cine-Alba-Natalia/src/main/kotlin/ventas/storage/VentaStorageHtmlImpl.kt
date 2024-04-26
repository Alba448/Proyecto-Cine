package org.example.ventas.storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.ventas.exceptions.VentaExceptions
import org.example.ventas.models.Venta
import org.koin.core.annotation.Named
import org.koin.core.annotation.Singleton
import org.lighthousegames.logging.logging
import java.io.File

/**
 * @author Alba Garcia
 */
private val logger = logging()

/**
 * Implementación de [VentaStorage] que exporta los datos de una venta a un archivo HTML.
 */
@Singleton
@Named("VentaStorageHtml")
class VentaStorageHtmlImpl : VentaStorage {
    /**
     * Exporta los datos de una venta a un archivo HTML.
     * @param venta la venta a exportar
     * @param file el archivo HTML de destino
     * @throws VentaNoAlmacenada si no se pudo almacenar la venta en formato HTML
     */
    @Throws(VentaExceptions.VentaNoAlmacenada::class)
    override fun export(venta: Venta, file: File) {
        try {
            val html = """
            <html>
                <head>
                    <title>Venta</title>
                    <meta charset="utf-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
                </head>
                <body>
                    <div class="container">
                        <h1>Venta</h1>
                        <p>Fecha: ${venta.createdAt.toDefaultDateTimeString()}</p>
                        <p>Socio: ${venta.socio.nombre}</p>
                        <p>Productos:</p>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Nombre</th>
                                    <th>Cantidad</th>
                                    <th>Precio Unitario</th>
                                    <th>Precio Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                ${
                venta.lineas.joinToString("") {
                    "<tr><td>${it.producto.nombre}</td><td>${it.cantidad}</td><td>${it.producto.precio.toDefaultMoneyString()}</td><td>${(it.cantidad * it.producto.precio).toDefaultMoneyString()}</td></tr>"
                }
            }
                            </tbody>
                        </table>
                        <p class="text-right lead">Total: <span style="font-weight: bold;">${venta.total.toDefaultMoneyString()}</span></p>
                    </div>
                </body>
            </html>
        """.trimIndent()
            file.writeText(html, Charsets.UTF_8)
        } catch (e: Exception) {
            logger.error { "Error al salvar venta a archivo HTML: ${file.absolutePath}. ${e.message}" }
            throw VentaExceptions.VentaNoAlmacenada("Error al salvar venta a archivo HTML: ${file.absolutePath}. ${e.message}")
        }
    }
}
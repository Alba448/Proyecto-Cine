package org.example

import org.example.productos.models.butacas.repositories.ButacaRepositoryImpl
import org.example.sala.services.SalaServiceImpl

fun main() {
    val salaService = SalaServiceImpl(butacaRepositoryImpl = ButacaRepositoryImpl())

    salaService.simular()
}
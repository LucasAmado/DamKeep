package com.lucasamado.damkeepapp.models

import java.util.*

data class Nota(
    val id: UUID,
    val titulo: String,
    val contenido: String,
    val fecha_creacion: String,
    val fecha_edicion: String,
    val autor: User
)
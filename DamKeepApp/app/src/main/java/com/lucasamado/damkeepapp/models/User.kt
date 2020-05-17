package com.lucasamado.damkeepapp.models

import java.util.*

data class User(
    val fullname: String,
    val id: UUID,
    val roles: String,
    val notas: List<Any>,
    val username: String
)
package com.salesianostriana.dam.damkeepapi.entidades

import java.time.LocalDate
import java.util.*

data class NotaDTO(
        val id: UUID?,
        val titulo: String,
        val contenido: String,
        val autor: User?,
        val fecha_edicion: LocalDate?
)

fun Nota.toNotaDto() = NotaDTO(id, titulo, contenido, autor, fecha_edicion)

fun NotaDTO.toNota() = Nota(titulo, contenido, autor, fecha_edicion, id)

data class NuevaNotaDto(
        val titulo: String,
        val contenido: String,
        val autor: User?,
        val fecha_edicion: LocalDate?
)

fun NuevaNotaDto.toNota() = Nota(titulo, contenido, autor, fecha_edicion)




data class UserDTO(
        var username : String,
        var fullName: String,
        var roles: String,
        val id: UUID? = null
)

fun User.toUserDTO() = UserDTO(username, fullName, roles.joinToString(), id)

data class CreateUserDTO(
        var username: String,
        var fullName: String,
        var avatar: String,
        val password: String,
        val password2: String
)
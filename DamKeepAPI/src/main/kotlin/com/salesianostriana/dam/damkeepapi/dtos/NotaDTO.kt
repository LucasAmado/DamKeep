package com.salesianostriana.dam.damkeep.dtos

import com.fasterxml.jackson.annotation.JsonFormat
import com.salesianostriana.dam.damkeep.entities.Nota
import com.salesianostriana.dam.damkeep.entities.User
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class NotaDTO(
        val id: UUID?,
        var titulo: String
)

fun Nota.toNotaDTO() = NotaDTO(id, titulo)

fun NotaDTO.toNota() = Nota(titulo)

data class NuevaNotaDTO(
        var titulo: String,
        var contenido: String,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        var fecha_creacion: LocalDate? = LocalDate.now()
)

fun NuevaNotaDTO.toNota(user: User) = Nota(titulo, contenido, fecha_creacion, autor = user)

data class NotaDetalleDTO(
        val id: UUID?,
        var titulo: String,
        var contenido: String?,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        var fecha_creacion: LocalDate?,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        var fecha_edicion: LocalDate?
)

fun Nota.toNotaDetailDTO() = NotaDetalleDTO(id, titulo, contenido, fecha_creacion, fecha_edicion)


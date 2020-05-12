package com.salesianostriana.dam.damkeepapi.entidades

import com.fasterxml.jackson.annotation.JsonBackReference
import java.time.LocalDate
import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

data class Nota (
        var titulo: String,
        var contenido: String,
        @JsonBackReference @ManyToOne var autor: User? = null,
        var fecha_edicion: LocalDate?,
        @Id @GeneratedValue val id: UUID? = null
){

    var fecha_creacion: LocalDate

    init {
        this.fecha_creacion = LocalDate.now()
    }
}
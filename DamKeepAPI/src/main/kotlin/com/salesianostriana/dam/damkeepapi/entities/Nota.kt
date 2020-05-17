package com.salesianostriana.dam.damkeep.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Nota(
    var titulo: String,

    var contenido: String? = null,

    var fecha_edicion: LocalDate? = null,

    var fecha_creacion: LocalDate? = null,

    @JsonBackReference @ManyToOne
    var autor: User? = null,

    @Id @GeneratedValue
    val id: UUID? = null
){

}
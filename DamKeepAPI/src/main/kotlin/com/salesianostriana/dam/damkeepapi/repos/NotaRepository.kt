package com.salesianostriana.dam.damkeepapi.repos

import com.salesianostriana.dam.damkeepapi.entidades.Nota
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface NotaRepository: JpaRepository<Nota, UUID> {

    @Query("select n from Nota n where n.autor.id = :id")
    fun findByIdUser(id: UUID): List<Nota>
}
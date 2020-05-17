package com.salesianostriana.dam.damkeep.repositories

import com.salesianostriana.dam.damkeep.entities.Nota
import com.salesianostriana.dam.damkeep.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface NotaRepository: JpaRepository<Nota,UUID> {

    fun findByAutor(user: User): List<Nota>

}
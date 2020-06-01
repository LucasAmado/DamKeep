package com.salesianostriana.dam.damkeepapi.repositories

import com.salesianostriana.dam.damkeepapi.entities.Nota
import com.salesianostriana.dam.damkeepapi.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface NotaRepository: JpaRepository<Nota,UUID> {

    fun findByAutor(user: User): List<Nota>

}
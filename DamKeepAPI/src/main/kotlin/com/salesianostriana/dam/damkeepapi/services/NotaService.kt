package com.salesianostriana.dam.damkeep.services

import com.salesianostriana.dam.damkeep.entities.Nota
import com.salesianostriana.dam.damkeep.entities.User
import com.salesianostriana.dam.damkeep.repositories.NotaRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class NotaService(
        private val repo: NotaRepository
){
    fun findByUser(user: User) = repo.findByAutor(user)

    fun findById(id : UUID) = repo.findById(id)

    fun crearNota(nota: Nota) = repo.save(nota)

    fun editarNota(nota: Nota) = repo.save(nota)

    fun eliminarNota(id: UUID) = repo.deleteById(id)

}
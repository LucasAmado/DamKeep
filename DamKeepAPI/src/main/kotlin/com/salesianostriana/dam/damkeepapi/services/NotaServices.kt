package com.salesianostriana.dam.damkeepapi.services

import com.salesianostriana.dam.damkeepapi.entidades.*
import com.salesianostriana.dam.damkeepapi.repos.NotaRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate
import java.util.*

@Service
class NotaServices(
        private val notaRepository: NotaRepository
) {

    fun crearNota(nuevaNota: NuevaNotaDto): Optional<Nota> {
        return Optional.of(
                with(nuevaNota) {
                    notaRepository.save(Nota(titulo, contenido, autor, LocalDate.now()))
                }
        )
    }

    fun findById(id: UUID) = notaRepository.findById(id)

    fun findByIdUser(id: UUID) = notaRepository.findByIdUser(id)

    fun editarNota(nota_editar: NuevaNotaDto, @PathVariable id: UUID): NotaDTO {
        return notaRepository.findById(id).map { nota_encontrada ->
            val nota_actualizada: Nota =
                    with(nota_editar) {
                        nota_encontrada.copy(
                                titulo = titulo,
                                contenido = contenido,
                                autor = autor,
                                fecha_edicion = LocalDate.now()
                        )
                    }
            notaRepository.save(nota_actualizada).toNotaDto()
        }.orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado la nota con id $id")
        }
    }

    fun deleteNota(@PathVariable id: UUID): ResponseEntity<Void> {
        notaRepository.deleteById(id)
        return ResponseEntity.noContent().build()
    }

}
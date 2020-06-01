package com.salesianostriana.dam.damkeepapi.controllers

import com.salesianostriana.dam.damkeepapi.dtos.*
import com.salesianostriana.dam.damkeepapi.entities.Nota
import com.salesianostriana.dam.damkeepapi.entities.User
import com.salesianostriana.dam.damkeepapi.services.NotaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/notas")
class NotaController(
        val notaService: NotaService
) {

    private fun loadNotas(user: User) : List<Nota> {
        var result: List<Nota> = notaService.findByUser(user)
        if (result.isEmpty())
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no tiene notas guardadas")
        return result
    }

    private fun oneNota(id: UUID) : NotaDetalleDTO {
        var result: Optional<Nota>
        with(notaService) {
            result =  findById(id)

        }
        if(result.isPresent) return result.get().toNotaDetailDTO()
        else throw  ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado la nota con el id $id")
    }


    @GetMapping("/")
    fun getNotas(@AuthenticationPrincipal user: User) = loadNotas(user).map {
        it.toNotaDTO()
    }

    @GetMapping("/{id}")
    fun findNota(@PathVariable id: UUID) = oneNota(id)

    @PostMapping("/")
    fun nuevaNota(@RequestBody nuevaNota: NuevaNotaDTO, @AuthenticationPrincipal user: User) =
            ResponseEntity.status(HttpStatus.CREATED).body(notaService.crearNota(nuevaNota.toNota(user)).toNotaDTO())

    @PutMapping("/{id}")
    fun editarNota(@RequestBody editarNota: NuevaNotaDTO, @PathVariable id: UUID): NotaDTO {
        return notaService.findById(id)
                .map { notaEncontrada ->
                    val notaActualizada: Nota =
                            with(editarNota){
                                notaEncontrada.copy(
                                        titulo = titulo,
                                        contenido = contenido,
                                        fecha_edicion = LocalDate.now()
                                )
                            }
                    notaService.editarNota(notaActualizada).toNotaDTO()
                }.orElseThrow {
                    ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado la nota con el id $id")
                }
    }

    @DeleteMapping("/{id}")
    fun borrarNota(@PathVariable id: UUID): ResponseEntity<Void> {
        notaService.eliminarNota(id)
        return ResponseEntity.noContent().build()
    }

}
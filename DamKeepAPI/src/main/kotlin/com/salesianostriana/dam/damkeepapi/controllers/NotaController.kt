package com.salesianostriana.dam.damkeepapi.controllers

import com.salesianostriana.dam.damkeepapi.entidades.*
import com.salesianostriana.dam.damkeepapi.repos.NotaRepository
import com.salesianostriana.dam.damkeepapi.repos.UserRepository
import com.salesianostriana.dam.damkeepapi.services.NotaServices
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/notas")
class NotaController(val notaServices: NotaServices, val usuarioRepository: UserRepository) {

    @GetMapping("/user/{id}")
    fun misNotas(@PathVariable id: UUID) = notaServices.findByIdUser(id)

    @GetMapping("/{id}")
    fun unaNota(@PathVariable id: UUID) = notaServices.findById(id)

    @PostMapping("/")
    fun nuevaNota(@RequestBody nuevaNota: NuevaNotaDto) = notaServices.crearNota(nuevaNota)

    @PutMapping("/{id}")
    fun editarNota(@RequestBody nota_editar: NuevaNotaDto, @PathVariable id: UUID): NotaDTO = notaServices.editarNota(nota_editar, id)

    @DeleteMapping("/{id}")
    fun eliminarSerie(@PathVariable id: UUID): ResponseEntity<Void> = notaServices.deleteNota(id)

}
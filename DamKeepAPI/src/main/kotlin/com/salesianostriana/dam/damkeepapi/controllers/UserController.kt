package com.salesianostriana.dam.damkeep.controllers

import com.salesianostriana.dam.damkeep.dtos.CreateUserDTO
import com.salesianostriana.dam.damkeep.dtos.UserDTO
import com.salesianostriana.dam.damkeep.dtos.toUserDTO
import com.salesianostriana.dam.damkeep.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/user")
class UserController(val userService: UserService) {

    @PostMapping("/signup")
    fun nuevoUsuario(@RequestBody newUser: CreateUserDTO): ResponseEntity<UserDTO> =
            userService.create(newUser).map { ResponseEntity.status(HttpStatus.CREATED).body(it.toUserDTO()) }.orElseThrow {
                ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de usuario ${newUser.username} ya existe")
            }
}
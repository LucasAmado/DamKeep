package com.salesianostriana.dam.damkeepapi.dtos

import com.salesianostriana.dam.damkeepapi.entities.Nota
import com.salesianostriana.dam.damkeepapi.entities.User
import java.util.*
import kotlin.collections.ArrayList

data class UserDTO(
        var username : String,
        var fullName: String,
        var roles: String,
        var notas: List<Nota>? = null,
        val id: UUID? = null

)

fun User.toUserDTO() = UserDTO(username, fullname, roles.joinToString(), ArrayList(), id)

data class CreateUserDTO(
        var username: String,
        var fullname: String,
        var password: String,
        val password2: String
)

fun CreateUserDTO.toUser() = User(username, fullname, password)
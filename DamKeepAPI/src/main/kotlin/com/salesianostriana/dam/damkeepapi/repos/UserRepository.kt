package com.salesianostriana.dam.damkeepapi.repos

import com.salesianostriana.dam.damkeepapi.entidades.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User, UUID> {

    fun findByUsername(username : String) : Optional<User>
}
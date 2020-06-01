package com.salesianostriana.dam.damkeepapi.repositories

import com.salesianostriana.dam.damkeepapi.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User, UUID> {

    fun findByUsername(username: String): Optional<User>
}
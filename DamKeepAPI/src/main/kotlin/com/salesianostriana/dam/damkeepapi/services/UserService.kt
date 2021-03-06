package com.salesianostriana.dam.damkeepapi.services

import com.salesianostriana.dam.damkeepapi.dtos.CreateUserDTO
import com.salesianostriana.dam.damkeepapi.entities.User
import com.salesianostriana.dam.damkeepapi.repositories.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
class UserService(
        private val repo: UserRepository,
        private val encoder: PasswordEncoder
) {

    fun create(newUser : CreateUserDTO): Optional<User> {
        if (findByUsername(newUser.username).isPresent)
            return Optional.empty()
        return Optional.of(
                with(newUser) {
                    repo.save(User(username, fullname, encoder.encode(password), "USER", ArrayList()))
                }

        )
    }

    fun findByUsername(username : String) = repo.findByUsername(username)

    fun findById(id : UUID) = repo.findById(id)

    fun save(user: User) = repo.save(user)

}
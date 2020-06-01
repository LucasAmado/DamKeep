package com.salesianostriana.dam.damkeepapi.controllers

import com.salesianostriana.dam.damkeepapi.dtos.CreateUserDTO
import com.salesianostriana.dam.damkeepapi.dtos.UserDTO
import com.salesianostriana.dam.damkeepapi.dtos.toUser
import com.salesianostriana.dam.damkeepapi.dtos.toUserDTO
import com.salesianostriana.dam.damkeepapi.entities.User
import com.salesianostriana.dam.damkeepapi.security.JwtTokenProvider
import com.salesianostriana.dam.damkeepapi.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@RestController
class AuthenticationController(
        private val authenticationManager: AuthenticationManager,
        private val jwtTokenProvider: JwtTokenProvider,
        private val userService: UserService,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

    @PostMapping("/auth/login")
    fun login(@Valid @RequestBody loginRequest: LoginRequest): JwtUserResponse {
        val authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        loginRequest.username, loginRequest.password
                )
        )

        SecurityContextHolder.getContext().authentication = authentication

        val user = authentication.principal as User
        val jwtToken = jwtTokenProvider.generateToken(authentication)

        return JwtUserResponse(jwtToken, user.toUserDTO())

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/me")
    fun me(@AuthenticationPrincipal user: User) = user.toUserDTO()

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/signup")
    fun signup(@RequestBody newUser: CreateUserDTO): UserDTO{
        val result = userService.findByUsername(newUser.username)
        if(result==null) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de usuario ${newUser.username} ya existe. Pruebe otro.")
        else {
            newUser.password= bCryptPasswordEncoder.encode(newUser.password)
            return userService.save(newUser.toUser()).toUserDTO()
        }
    }
}


data class LoginRequest(
        @NotBlank val username: String,
        @NotBlank val password: String
)

data class JwtUserResponse(
        val token: String,
        val user: UserDTO
)
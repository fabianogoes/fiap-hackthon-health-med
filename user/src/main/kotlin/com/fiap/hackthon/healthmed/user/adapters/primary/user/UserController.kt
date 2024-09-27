package com.fiap.hackthon.healthmed.user.adapters.primary.user

import com.fiap.hackthon.healthmed.user.ports.CreateUserPort
import com.fiap.hackthon.healthmed.user.ports.UserDeletePort
import com.fiap.hackthon.healthmed.user.ports.UserReadingPort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userCreateUserPort: CreateUserPort,
    private val userReadingPort: UserReadingPort,
    private val userDeleteUserPort: UserDeletePort,
) {
    @PostMapping
    fun create(
        @RequestBody userRequest: UserRequest,
    ): UserResponse =
        userCreateUserPort.create(
            email = userRequest.email,
            password = userRequest.password,
            role = userRequest.role,
        ).toResponse()

    @GetMapping
    fun listAll(): List<UserResponse> =
        userReadingPort
            .findAll()
            .map { it.toResponse() }

    @GetMapping("/email/{email}")
    fun findByEmail(
        @PathVariable email: String,
    ): UserResponse =
        userReadingPort
            .findByEmail(email)
            ?.toResponse()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.")

    @DeleteMapping("/{email}")
    fun deleteByEmail(
        @PathVariable email: String,
    ): ResponseEntity<Boolean> {
        userDeleteUserPort.deleteByEmail(email)
        return ResponseEntity.noContent().build()
    }
}

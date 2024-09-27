package com.fiap.hackthon.healthmed.user.adapters.secondary

import com.fiap.hackthon.healthmed.shared.Email
import com.fiap.hackthon.healthmed.user.domain.entity.User
import com.fiap.hackthon.healthmed.user.ports.UserPersistencePort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class UserPostgresPersistence(
    private val userRepository: UserPostgresRepository,
) : UserPersistencePort {
    override fun save(user: User): User =
        userRepository
            .save(user.toDBO())
            .toEntity()

    override fun findByEmail(email: Email): User? =
        userRepository
            .findByIdOrNull(email.value)
            ?.toEntity()

    override fun findAll() =
        userRepository
            .findAll()
            .map(UserDBO::toEntity)

    override fun deleteByEmail(email: Email) {
        userRepository.deleteById(email.value)
    }
}

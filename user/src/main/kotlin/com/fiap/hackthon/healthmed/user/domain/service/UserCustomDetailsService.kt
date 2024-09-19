package com.fiap.hackthon.healthmed.user.domain.service

import com.fiap.hackthon.healthmed.shared.Email
import com.fiap.hackthon.healthmed.user.ports.UserPersistencePort
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

typealias ApplicationUser = com.fiap.hackthon.healthmed.user.domain.entity.User

@Service
class CustomUserDetailsService(
  private val userPersistencePort: UserPersistencePort,
) : UserDetailsService {

  override fun loadUserByUsername(username: String): UserDetails =
    userPersistencePort.findByEmail(Email(username))
      ?.mapToUserDetails()
      ?: throw UsernameNotFoundException("Not found!")

  private fun ApplicationUser.mapToUserDetails(): UserDetails =
    User.builder()
      .username(this.email.value)
      .password(this.password)
      .roles(this.role.name)
      .build()
}
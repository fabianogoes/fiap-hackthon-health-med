package com.fiap.hackthon.healthmed.user.adapters.primary.user

import com.fiap.hackthon.healthmed.user.domain.entity.User

data class UserResponse(
  val email: String,
  val role: String,
)

fun User.toResponse(): UserResponse =
  UserResponse(
    email = this.email.value,
    role = this.role.name,
  )
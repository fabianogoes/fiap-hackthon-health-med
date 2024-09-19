package com.fiap.hackthon.healthmed.user.adapters.primary.user

data class UserRequest(
  val email: String,
  val password: String,
  val role: String,
)
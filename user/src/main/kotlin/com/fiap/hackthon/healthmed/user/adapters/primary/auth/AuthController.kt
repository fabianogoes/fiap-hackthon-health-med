package com.fiap.hackthon.healthmed.user.adapters.primary.auth

import com.fiap.hackthon.healthmed.user.ports.AuthenticationPort
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/auth")
class AuthController(
  private val authenticationPort: AuthenticationPort
) {

  @PostMapping
  fun authenticate(
    @RequestBody authRequest: AuthRequest
  ): AuthResponse =
    authenticationPort
      .authenticate(authRequest.email, authRequest.password)
      .let { AuthResponse(it) }

  @PostMapping("/refresh")
  fun refreshAccessToken(
    @RequestBody request: TokenRefreshRequest
  ): TokenResponse =
    authenticationPort.refreshAccessToken(request.token)
      ?.mapToTokenResponse()
      ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid refresh token.")

  private fun String.mapToTokenResponse(): TokenResponse =
    TokenResponse(
      token = this
    )

}
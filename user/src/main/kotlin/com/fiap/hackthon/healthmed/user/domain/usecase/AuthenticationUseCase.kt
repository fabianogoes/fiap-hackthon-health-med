package com.fiap.hackthon.healthmed.user.domain.usecase

import com.fiap.hackthon.healthmed.shared.Email
import com.fiap.hackthon.healthmed.user.domain.service.CustomUserDetailsService
import com.fiap.hackthon.healthmed.user.domain.service.TokenService
import com.fiap.hackthon.healthmed.user.adapters.primary.auth.JwtProperties
import com.fiap.hackthon.healthmed.user.adapters.secondary.RefreshTokenRepository
import com.fiap.hackthon.healthmed.user.ports.AuthenticationPort
import com.fiap.hackthon.healthmed.user.ports.UserPersistencePort
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class AuthenticationUseCase(
  private val authManager: AuthenticationManager,
  private val userDetailsService: CustomUserDetailsService,
  private val userPersistencePort: UserPersistencePort,
  private val tokenService: TokenService,
  private val jwtProperties: JwtProperties,
  private val refreshTokenRepository: RefreshTokenRepository,
) : AuthenticationPort{

  override fun authenticate(email: String, password: String): String {
    authManager.authenticate(UsernamePasswordAuthenticationToken(email, password))

    val user = userDetailsService.loadUserByUsername(email)

    val accessToken = createAccessToken(user)
    val refreshToken = createRefreshToken(user)

    refreshTokenRepository
      .save(refreshToken, user)
      .also { updateUserWithLastToken(user, accessToken) }

    return accessToken
  }

  override fun refreshAccessToken(refreshToken: String): String? {
    val extractedEmail = tokenService.extractEmail(refreshToken)

    return extractedEmail?.let { email ->
      val currentUserDetails = userDetailsService.loadUserByUsername(email)
      val refreshTokenUserDetails = refreshTokenRepository.findUserDetailsByToken(refreshToken)

      if (!tokenService.isExpired(refreshToken) && refreshTokenUserDetails?.username == currentUserDetails.username)
        createAccessToken(currentUserDetails)
      else
        null
    }
  }

  private fun updateUserWithLastToken(userDetails: UserDetails, lastToken: String) {
    userPersistencePort
      .findByEmail(Email(userDetails.username))!!
      .also {
        val user = it.copy(lastToken = lastToken, lastTokenAt = LocalDateTime.now())
        userPersistencePort.save(user)
      }
  }

  private fun createAccessToken(user: UserDetails) = tokenService.generate(
    userDetails = user,
    expirationDate = getAccessTokenExpiration()
  )

  private fun createRefreshToken(user: UserDetails) = tokenService.generate(
    userDetails = user,
    expirationDate = getRefreshTokenExpiration()
  )

  private fun getAccessTokenExpiration(): Date =
    Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)

  private fun getRefreshTokenExpiration(): Date =
    Date(System.currentTimeMillis() + jwtProperties.refreshTokenExpiration)

}
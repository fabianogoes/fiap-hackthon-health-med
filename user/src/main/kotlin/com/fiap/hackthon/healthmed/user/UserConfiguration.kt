package com.fiap.hackthon.healthmed.user

import com.fiap.hackthon.healthmed.user.adapters.primary.auth.JwtProperties
import com.fiap.hackthon.healthmed.user.domain.service.CustomUserDetailsService
import com.fiap.hackthon.healthmed.user.ports.UserPersistencePort
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties::class)
class UserConfiguration {

    @Bean
    fun userDetailsService(userPersistencePort: UserPersistencePort): UserDetailsService =
        CustomUserDetailsService(userPersistencePort)

    @Bean
    fun encoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationProvider(userPersistencePort: UserPersistencePort): AuthenticationProvider =
        DaoAuthenticationProvider()
            .also {
                it.setUserDetailsService(userDetailsService(userPersistencePort))
                it.setPasswordEncoder(encoder())
            }

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager =
        config.authenticationManager

}
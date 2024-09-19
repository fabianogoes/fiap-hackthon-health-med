package com.fiap.hackthon.healthmed.user

import com.fiap.hackthon.healthmed.user.adapters.primary.auth.AuthJwtFilter
import com.fiap.hackthon.healthmed.user.domain.entity.User
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val authenticationProvider: AuthenticationProvider,
) {

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        authJwtFilter: AuthJwtFilter
    ): DefaultSecurityFilterChain {
        http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers( "/api/auth", "api/auth/refresh", "/error", "/health",)
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/users")
                    .permitAll()
                    .requestMatchers("/api/users**")
                    .hasRole(User.Role.ADMIN_ROLE.name)
                    .anyRequest()
                    .fullyAuthenticated()
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(authJwtFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}

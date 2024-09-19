package com.fiap.hackthon.healthmed.user.adapters.secondary

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserPostgresRepository : JpaRepository<UserDBO, String>
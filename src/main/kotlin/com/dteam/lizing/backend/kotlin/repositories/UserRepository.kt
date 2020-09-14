package com.dteam.lizing.backend.kotlin.repositories

import com.dteam.lizing.backend.kotlin.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional

@Repository
interface UserRepository: JpaRepository<User, Long> {

    fun existsByUsername(@Param("username") username: String): Boolean

    fun findByUsername(@Param("username") username: String): Optional<User>

    fun findOneByUsername(username: String): User?

    fun findByEmail(@Param("email") email: String): Optional<User>

    @Transactional
    fun deleteByUsername(@Param("username") username: String)

}
package com.dteam.lizing.backend.kotlin.repositories

import com.dteam.lizing.backend.kotlin.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional

@Repository
interface UserRepository: JpaRepository<User, Long> {

    fun existsByUserName(@Param("userName") userName: String): Boolean

    fun findByUserName(@Param("userName") userName: String): Optional<User>

    fun findOneByUserName(userName: String): User?

    fun findByEmail(@Param("email") email: String): Optional<User>

    @Transactional
    fun deleteByUserName(@Param("userName") userName: String)

}
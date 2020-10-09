package com.dteam.lizing.backend.kotlin.repositories

import com.dteam.lizing.backend.kotlin.entities.Privilege
import com.dteam.lizing.backend.kotlin.entities.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PrivilegeRepository : JpaRepository<Privilege, Long> {
    fun findByName(name: String): Privilege?
}
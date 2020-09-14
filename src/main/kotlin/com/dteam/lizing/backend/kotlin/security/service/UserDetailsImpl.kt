package com.dteam.lizing.backend.kotlin.security.service

import com.dteam.lizing.backend.kotlin.entities.Privilege
import com.dteam.lizing.backend.kotlin.entities.Role
import com.dteam.lizing.backend.kotlin.entities.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import java.util.stream.Collectors


class UserDetailsImpl(private val user: User) : UserDetails {

    private fun getPrivileges(roles: Collection<Role>): List<String>? {
        val privileges: MutableList<String> = ArrayList()
        val collection: MutableList<Privilege> = ArrayList()
        for (role in roles) {
            role.privilege?.let { collection.addAll(it) }
        }
        for (item in collection) {
            item.name?.let { privileges.add(it) }
        }
        return privileges
    }


    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return user.roles!!.stream().map { role -> SimpleGrantedAuthority(role.name) }.collect(Collectors.toList<GrantedAuthority>())
    }

    override fun getPassword(): String {
        return user.password!!
    }

    override fun getUsername(): String {
        return user.username!!
    }

    override fun isAccountNonExpired(): Boolean {
        return false
    }

    override fun isAccountNonLocked(): Boolean {
        return false
    }

    override fun isCredentialsNonExpired(): Boolean {
        return false
    }

    override fun isEnabled(): Boolean {
        return false
    } //...
}
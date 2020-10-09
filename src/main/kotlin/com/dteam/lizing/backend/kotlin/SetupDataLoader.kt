package com.dteam.lizing.backend.kotlin

import ch.qos.logback.core.joran.spi.ConsoleTarget.findByName
import com.dteam.lizing.backend.kotlin.entities.Privilege
import com.dteam.lizing.backend.kotlin.entities.Role
import com.dteam.lizing.backend.kotlin.entities.User
import com.dteam.lizing.backend.kotlin.repositories.PrivilegeRepository
import com.dteam.lizing.backend.kotlin.repositories.RoleRepository
import com.dteam.lizing.backend.kotlin.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.util.*
import javax.transaction.Transactional


@Component
class SetupDataLoader : ApplicationListener<ContextRefreshedEvent?> {
    var alreadySetup = false

    @Autowired
    private val userRepository: UserRepository? = null

    @Autowired
    private val roleRepository: RoleRepository? = null

    @Autowired
    private val privilegeRepository: PrivilegeRepository? = null

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    @Transactional
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        if (alreadySetup) return
        val readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE")
        val writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE")
        val adminPrivileges: List<Privilege?> = listOf(
                readPrivilege, writePrivilege)
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges)
        createRoleIfNotFound("ROLE_USER", listOf(readPrivilege))
        val adminRole: Role? = roleRepository!!.findByName("ROLE_ADMIN")
        val user = User("User Name", "First Name", "Last Name", "test@test.com", passwordEncoder!!.encode("test"), true)

        if (adminRole != null) {
            user.roles = listOf(adminRole)
        }

        userRepository!!.save(user)
        alreadySetup = true
    }

    @Transactional
    fun createPrivilegeIfNotFound(name: String?): Privilege {
        var privilege: Privilege? = privilegeRepository!!.findByName(name!!)
        if (privilege == null) {
            privilege = Privilege(name)
            privilegeRepository.save(privilege)
        }
        return privilege
    }

    @Transactional
    fun createRoleIfNotFound(
            name: String?, privileges: Collection<Privilege?>?): Role {
        var role: Role? = roleRepository!!.findByName(name!!)
        if (role == null) {
            role = Role(name)
            role.privilege = privileges as Collection<Privilege>?
            roleRepository.save(role)
        }
        return role
    }
}
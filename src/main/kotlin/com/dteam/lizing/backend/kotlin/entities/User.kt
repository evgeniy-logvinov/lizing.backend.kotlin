package com.dteam.lizing.backend.kotlin.entities

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
        @Column(nullable = false)
        var userName: String,
        var firstName: String,
        var lastName: String,
        var email: String,
        var password: String,
        var enabled: Boolean,

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "users_roles",
                joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
        )
        var roles: Collection<Role>? = null
) {

    @Id
    @GeneratedValue
    var id: Long = 0
}
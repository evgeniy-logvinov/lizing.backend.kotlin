package com.dteam.lizing.backend.kotlin.entities

import javax.persistence.*

@Entity
@Table(name = "roles")
data class Role(
        @Column(nullable = false)
        val name: String,

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "roles_privilege",
                joinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "privilege_id", referencedColumnName = "id")]
        )
        var privilege: Collection<Privilege>? = null
) {
    @Id
    @GeneratedValue
    val id: Long = 0
}
package com.dteam.lizing.backend.kotlin.entities

import javax.persistence.*

@Entity
@Table(name = "roles")
data class Role(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = 0,

        @Column(name = "name", nullable = false)
        val name: String? = null,

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "roles_privilege",
                joinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "privilege_id", referencedColumnName = "id")]
        )
        var privilege: Collection<Privilege>? = null

)
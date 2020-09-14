package com.dteam.lizing.backend.kotlin.entities

import javax.persistence.*

@Entity
@Table(name = "privilege")
data class Privilege (

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = 0,

        @Column(name="name", nullable = false)
        val name: String? = null

)
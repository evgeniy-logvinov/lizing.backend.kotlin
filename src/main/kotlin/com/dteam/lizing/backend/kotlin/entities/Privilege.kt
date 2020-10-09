package com.dteam.lizing.backend.kotlin.entities

import javax.persistence.*

@Entity
@Table(name = "privilege")
data class Privilege (
        @Column(nullable = false)
        val name: String
) {
        @Id
        @GeneratedValue
        val id: Long = 0
}
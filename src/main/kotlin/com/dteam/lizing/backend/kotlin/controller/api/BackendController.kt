package com.dteam.lizing.backend.kotlin.controller.api

import com.dteam.lizing.backend.kotlin.entities.Greeting
import com.dteam.lizing.backend.kotlin.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
@RequestMapping("/api/v1/roles")
class RolesController {

    @GetMapping("/")
    fun getNone() = "NONE"

    @GetMapping("/user")
    fun getUser() = "USER"

    @GetMapping("/admin")
    fun getAdmin() = "ADMIN"
}
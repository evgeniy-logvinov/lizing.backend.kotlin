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
@RequestMapping("/api/v1/test")
class BackendController {

    val counter = AtomicLong()

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
            Greeting(counter.incrementAndGet(), "Hello, $name")

    @Autowired
    lateinit var personRepository: UserRepository

    @GetMapping("/persons")
    fun getPersons() = personRepository.findAll()

    @GetMapping("/test")
    fun getTests() = "Test"
}
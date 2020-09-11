package com.dteam.lizing.backend.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LizingApplication

fun main(args: Array<String>) {
	runApplication<LizingApplication>(*args)
}

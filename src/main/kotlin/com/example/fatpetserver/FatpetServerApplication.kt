package com.example.fatpetserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FatpetServerApplication

fun main(args: Array<String>) {
    runApplication<FatpetServerApplication>(*args)
}

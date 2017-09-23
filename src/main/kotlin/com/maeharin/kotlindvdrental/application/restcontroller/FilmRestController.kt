package com.maeharin.kotlindvdrental.application.restcontroller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/films")
class FilmRestController {
    @GetMapping
    fun index():String {
        return "hello"
    }
}
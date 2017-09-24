package com.maeharin.kotlindvdrental.application.restcontroller

import com.maeharin.kotlindvdrental.application.restcontroller.resource.FilmResource
import com.maeharin.kotlindvdrental.domain.repository.FilmRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/films")
class FilmRestController(
    private val filmRepository: FilmRepository
) {
    @GetMapping
    fun index(): List<FilmResource>
        = filmRepository.findAll().map(::FilmResource)

    @GetMapping("{id}")
    fun show(@PathVariable id: Int): FilmResource
        = filmRepository.findById(id)?.let { FilmResource(it) } ?: throw RuntimeException("not found")
}

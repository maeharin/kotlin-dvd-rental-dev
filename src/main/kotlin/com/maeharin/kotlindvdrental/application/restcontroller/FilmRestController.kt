package com.maeharin.kotlindvdrental.application.restcontroller

import com.maeharin.kotlindvdrental.application.applicationservice.FilmApplicationService
import com.maeharin.kotlindvdrental.application.restcontroller.param.FilmRestParam
import com.maeharin.kotlindvdrental.application.restcontroller.resource.FilmResource
import com.maeharin.kotlindvdrental.domain.command.FilmCommand
import com.maeharin.kotlindvdrental.domain.repository.FilmRepository
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/films")
class FilmRestController(
    private val filmApplicationService: FilmApplicationService,
    private val filmRepository: FilmRepository
) {
    @GetMapping
    fun index(): List<FilmResource>
        = filmRepository.findAll().map(::FilmResource)

    @GetMapping("{id}")
    fun show(@PathVariable id: Int): FilmResource
        = filmRepository.findById(id)?.let { FilmResource(it) } ?: throw RuntimeException("not found")

    @PostMapping
    fun create(@RequestBody @Validated filmRestParam: FilmRestParam): Int {
        val createCommand = FilmCommand(filmRestParam)
        return filmApplicationService.create(createCommand)
    }

    @PutMapping("{id}")
    fun update(
        @PathVariable id: Int,
        @RequestBody @Validated filmRestParam: FilmRestParam
    ) {
        val command = FilmCommand(filmRestParam).also { it.id = id }
        return filmApplicationService.update(command)
    }
}

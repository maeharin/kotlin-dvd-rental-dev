package com.maeharin.kotlindvdrental.application.restcontroller

import com.maeharin.kotlindvdrental.application.applicationservice.FilmApplicationService
import com.maeharin.kotlindvdrental.application.restcontroller.param.FilmCreateParam
import com.maeharin.kotlindvdrental.application.restcontroller.resource.FilmResource
import com.maeharin.kotlindvdrental.domain.command.FilmCreateCommand
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
    fun create(@RequestBody @Validated filmCreateParam: FilmCreateParam): Int {
        val createCommand = FilmCreateCommand(filmCreateParam)
        return filmApplicationService.create(createCommand)
    }
}

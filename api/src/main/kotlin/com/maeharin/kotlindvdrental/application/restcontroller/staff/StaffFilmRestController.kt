package com.maeharin.kotlindvdrental.application.restcontroller.staff

import com.maeharin.kotlindvdrental.application.applicationservice.FilmApplicationService
import com.maeharin.kotlindvdrental.application.applicationservice.command.FilmCommand
import com.maeharin.kotlindvdrental.application.restcontroller.staff.param.FilmRestParam
import com.maeharin.kotlindvdrental.application.restcontroller.staff.resource.FilmResource
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/staff/films")
@Api(tags = arrayOf("film"), description = "映画API")
class StaffFilmRestController(
        private val filmApplicationService: FilmApplicationService
) {
    @GetMapping
    @ApiOperation("映画一覧取得", nickname = "staff_get_films")
    fun index(): List<FilmResource>
            = filmApplicationService.findAll().map(::FilmResource)

    @GetMapping("{id}")
    @ApiOperation("映画詳細取得", nickname = "staff_get_film")
    fun show(@PathVariable id: Int): FilmResource
            = filmApplicationService.findById(id).let { FilmResource(it) }

    @GetMapping("search")
    @ApiOperation("映画検索", nickname = "staff_search_films")
    fun search(): List<FilmResource>
            = filmApplicationService.search().map(::FilmResource)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("映画作成", nickname = "staff_create_film")
    fun create(@RequestBody @Validated filmRestParam: FilmRestParam): Int {
        val command = FilmCommand(
                title = filmRestParam.title,
                description = filmRestParam.description,
                releaseYear = filmRestParam.releaseYear,
                rentalDuration = filmRestParam.rentalDuration,
                rentalRate = filmRestParam.rentalRate,
                length = filmRestParam.length,
                replacementCost = filmRestParam.replacementCost,
                languageId = filmRestParam.languageId,
                actorIds = filmRestParam.actorIds,
                categoryIds = filmRestParam.categoryIds
        )

        return filmApplicationService.create(command)
    }

    @PutMapping("{id}")
    @ApiOperation("映画編集", nickname = "staff_update_film")
    fun update(
            @PathVariable id: Int,
            @RequestBody @Validated filmRestParam: FilmRestParam
    ) {
        val command = FilmCommand(
                id = id,
                title = filmRestParam.title,
                description = filmRestParam.description,
                releaseYear = filmRestParam.releaseYear,
                rentalDuration = filmRestParam.rentalDuration,
                rentalRate = filmRestParam.rentalRate,
                length = filmRestParam.length,
                replacementCost = filmRestParam.replacementCost,
                languageId = filmRestParam.languageId,
                actorIds = filmRestParam.actorIds,
                categoryIds = filmRestParam.categoryIds
        )

        return filmApplicationService.update(command)
    }

    @DeleteMapping("{id}")
    @ApiOperation("映画削除", nickname = "staff_delete_film")
    fun delete(@PathVariable id: Int)
            = filmApplicationService.delete(id)

}

package com.maeharin.kotlindvdrental.application.restcontroller.customer

import com.maeharin.kotlindvdrental.application.applicationservice.FilmApplicationService
import com.maeharin.kotlindvdrental.application.restcontroller.customer.resource.FilmResource
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/customer/films")
@Api(tags = arrayOf("film"), description = "映画API")
class CustomerFilmRestController(
    private val filmApplicationService: FilmApplicationService
) {
    /**
     * 一覧
     */
    @GetMapping
    @ApiOperation("映画一覧取得", nickname = "customer_get_films")
    fun index(): List<FilmResource>
        = filmApplicationService.findAll().map(::FilmResource)

    /**
     * 詳細
     */
    @GetMapping("{id}")
    @ApiOperation("映画詳細取得", nickname = "customer_get_film")
    fun show(@PathVariable id: Int): FilmResource
        = filmApplicationService.findById(id).let { FilmResource(it) }

    /**
     * 検索
     */
    @GetMapping("search")
    @ApiOperation("映画検索", nickname = "customer_search_films")
    fun search(): List<FilmResource>
        = filmApplicationService.search().map(::FilmResource)
}

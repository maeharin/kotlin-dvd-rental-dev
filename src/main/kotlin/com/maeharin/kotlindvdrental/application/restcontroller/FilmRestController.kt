package com.maeharin.kotlindvdrental.application.restcontroller

import com.maeharin.kotlindvdrental.application.applicationservice.FilmApplicationService
import com.maeharin.kotlindvdrental.application.restcontroller.param.FilmRestParam
import com.maeharin.kotlindvdrental.application.restcontroller.resource.FilmResource
import com.maeharin.kotlindvdrental.domain.command.FilmCommand
import com.maeharin.kotlindvdrental.domain.repository.doma.FilmDomaRepository
import com.maeharin.kotlindvdrental.domain.repository.elasticsearch.FilmElasticSearchRepository
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/films")
@Api(tags = arrayOf("film"), description = "映画API")
class FilmRestController(
    private val filmApplicationService: FilmApplicationService,
    private val filmRepository: FilmDomaRepository,
    private val filmElasticSearchRepository: FilmElasticSearchRepository
) {
    /**
     * 一覧
     */
    @GetMapping
    @ApiOperation("映画一覧取得", nickname = "get_films")
    fun index(): List<FilmResource>
        = filmRepository.findAll().map(::FilmResource)

    /**
     * 詳細
     */
    @GetMapping("{id}")
    @ApiOperation("映画詳細取得", nickname = "get_film")
    fun show(@PathVariable id: Int): FilmResource
        = filmRepository.findById(id)?.let { FilmResource(it) } ?: throw RuntimeException("not found")

    /**
     * 検索
     */
    @GetMapping("search")
    @ApiOperation("映画検索", nickname = "search_films")
    fun search(): List<FilmResource>
        = filmElasticSearchRepository.search().map(::FilmResource)

    /**
     * 作成
     */
    @PostMapping
    @ApiOperation("映画作成", nickname = "create_film")
    fun create(@RequestBody @Validated filmRestParam: FilmRestParam): Int {
        val createCommand = FilmCommand(filmRestParam)
        return filmApplicationService.create(createCommand)
    }

    /**
     * 編集
     */
    @PutMapping("{id}")
    @ApiOperation("映画編集", nickname = "update_film")
    fun update(
        @PathVariable id: Int,
        @RequestBody @Validated filmRestParam: FilmRestParam
    ) {
        val command = FilmCommand(filmRestParam).also { it.id = id }
        return filmApplicationService.update(command)
    }

    /**
     * 削除
     */
    @DeleteMapping("{id}")
    @ApiOperation("映画削除", nickname = "delete_film")
    fun delete(@PathVariable id: Int) {
        return filmApplicationService.delete(id)
    }

    /**
     * ElascitSearchにインデックス
     * TODO: batch化
     */
    @GetMapping("index-to-elasticsearch")
    @ApiOperation("映画ElasticSearchにインデックス", nickname = "index_films_to_elasticsearch")
    fun indexToElasticSearch() {
        filmApplicationService.indexToElasticSearch()
    }
}

package com.maeharin.kotlindvdrental.application.restcontroller

import com.maeharin.kotlindvdrental.application.applicationservice.FilmApplicationService
import com.maeharin.kotlindvdrental.application.restcontroller.param.FilmRestParam
import com.maeharin.kotlindvdrental.application.restcontroller.resource.FilmResource
import com.maeharin.kotlindvdrental.domain.command.FilmCommand
import com.maeharin.kotlindvdrental.domain.repository.FilmRepository
import com.maeharin.kotlindvdrental.domain.repository.elasticsearch.ElasticSearchFilmRepository
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/films")
class FilmRestController(
    private val filmApplicationService: FilmApplicationService,
    private val filmRepository: FilmRepository,
    private val elasticSearchFilmRepository: ElasticSearchFilmRepository
) {
    /**
     * 一覧
     */
    @GetMapping
    fun index(): List<FilmResource>
        = filmRepository.findAll().map(::FilmResource)

    /**
     * 詳細
     */
    @GetMapping("{id}")
    fun show(@PathVariable id: Int): FilmResource
        = filmRepository.findById(id)?.let { FilmResource(it) } ?: throw RuntimeException("not found")

    /**
     * 検索
     */
    @GetMapping("search")
    fun search(): List<FilmResource>
        = elasticSearchFilmRepository.search().map(::FilmResource)

    /**
     * 作成
     */
    @PostMapping
    fun create(@RequestBody @Validated filmRestParam: FilmRestParam): Int {
        val createCommand = FilmCommand(filmRestParam)
        return filmApplicationService.create(createCommand)
    }

    /**
     * 変更
     */
    @PutMapping("{id}")
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
    fun delete(@PathVariable id: Int) {
        return filmApplicationService.delete(id)
    }

    /**
     * ElascitSearchにインデックス
     * TODO: batch化
     */
    @GetMapping("index-to-elasticsearch")
    fun indexToElasticSearch() {
        filmApplicationService.indexToElasticSearch()
    }
}

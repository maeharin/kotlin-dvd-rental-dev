package com.maeharin.kotlindvdrental.application.applicationservice

import com.maeharin.kotlindvdrental.domain.command.FilmCommand
import com.maeharin.kotlindvdrental.domain.model.Film
import com.maeharin.kotlindvdrental.domain.repository.doma.ActorDomaRepository
import com.maeharin.kotlindvdrental.domain.repository.doma.CategoryDomaRepository
import com.maeharin.kotlindvdrental.domain.repository.doma.FilmDomaRepository
import com.maeharin.kotlindvdrental.domain.repository.doma.LanguageRepository
import com.maeharin.kotlindvdrental.domain.repository.elasticsearch.FilmElasticSearchRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class FilmApplicationService(
    private val filmRepository: FilmDomaRepository,
    private val languageRepository: LanguageRepository,
    private val actorRepository: ActorDomaRepository,
    private val categoryRepository: CategoryDomaRepository,
    private val filmElasticSearchRepository: FilmElasticSearchRepository
) {
    fun create(command: FilmCommand): Int {
        // TODO: 作成者を取得してセット

        val language   = languageRepository.findById(command.languageId) ?: throw RuntimeException("language not found")
        val actors     = actorRepository.findByIds(command.actorIds)
        val categories = categoryRepository.findByIds(command.categoryIds)

        val film = Film(command = command, language = language, actors = actors, categories = categories)
        val filmId = filmRepository.store(film)

        // TODO: mail送信

        return filmId
    }

    fun update(command: FilmCommand) {
        // TODO: 権限チェック
        // TODO: レコード存在チェック

        val language   = languageRepository.findById(command.languageId) ?: throw RuntimeException("language not found")
        val actors     = actorRepository.findByIds(command.actorIds)
        val categories = categoryRepository.findByIds(command.categoryIds)

        val film = Film(command = command, language = language, actors = actors, categories = categories)
        filmRepository.update(film)

        // TODO: mail送信
    }

    fun delete(id: Int) {
        // TODO: 権限チェック
        // TODO: レコード存在チェック
        filmRepository.delete(id)
    }

    fun indexToElasticSearch() {
        val films = filmRepository.findAll()
        filmElasticSearchRepository.bulkIndex(films)
    }
}
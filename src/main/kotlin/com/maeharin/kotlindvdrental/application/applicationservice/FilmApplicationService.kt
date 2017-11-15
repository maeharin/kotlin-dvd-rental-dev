package com.maeharin.kotlindvdrental.application.applicationservice

import com.maeharin.kotlindvdrental.application.exception.RecordNotFoundException
import com.maeharin.kotlindvdrental.application.applicationservice.command.FilmCommand
import com.maeharin.kotlindvdrental.domain.model.Film
import com.maeharin.kotlindvdrental.domain.repository.ActorRepository
import com.maeharin.kotlindvdrental.domain.repository.FilmRepository
import com.maeharin.kotlindvdrental.domain.repository.LanguageRepository
import com.maeharin.kotlindvdrental.domain.repository.CategoryRepository
import com.maeharin.kotlindvdrental.infrastructure.elasticsearch.FilmRepositoryElasticSearchImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class FilmApplicationService(
        private val filmRepository: FilmRepository,
        private val languageRepository: LanguageRepository,
        private val actorRepository: ActorRepository,
        private val categoryRepository: CategoryRepository,
        private val filmRepositoryElasticSearchImpl: FilmRepositoryElasticSearchImpl
) {
    fun findAll(): List<Film> {
        return filmRepository.findAll()
    }

    fun search(): List<Film> {
        return filmRepositoryElasticSearchImpl.search()
    }

    fun findById(id: Int): Film {
        return filmRepository.findById(id) ?: throw RecordNotFoundException()
    }

    fun create(command: FilmCommand): Int {
        // TODO: 作成者を取得してセット

        val language   = languageRepository.findById(command.languageId) ?: throw RecordNotFoundException()
        val actors     = actorRepository.findByIds(command.actorIds)
        val categories = categoryRepository.findByIds(command.categoryIds)

        val film = Film(
                title = command.title,
                description = command.description,
                releaseYear = command.releaseYear,
                rentalDuration = command.rentalDuration,
                rentalRate = command.rentalRate,
                length = command.length,
                replacementCost = command.replacementCost,
                language = language,
                actors = actors,
                categories = categories
        )

        val filmId = filmRepository.store(film)

        // TODO: mail送信

        return filmId
    }

    fun update(command: FilmCommand) {
        // TODO: 権限チェック
        // TODO: レコード存在チェック

        val language   = languageRepository.findById(command.languageId) ?: throw RecordNotFoundException()
        val actors     = actorRepository.findByIds(command.actorIds)
        val categories = categoryRepository.findByIds(command.categoryIds)

        val film = Film(
                id = command.id,
                title = command.title,
                description = command.description,
                releaseYear = command.releaseYear,
                rentalDuration = command.rentalDuration,
                rentalRate = command.rentalRate,
                length = command.length,
                replacementCost = command.replacementCost,
                language = language,
                actors = actors,
                categories = categories
        )

        filmRepository.update(film)

        // TODO: mail送信
    }

    fun delete(id: Int) {
        // TODO: 権限チェック
        // TODO: レコード存在チェック
        filmRepository.delete(id)
    }
}
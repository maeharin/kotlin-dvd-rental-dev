package com.maeharin.kotlindvdrental.application.applicationservice

import com.maeharin.kotlindvdrental.domain.command.FilmCommand
import com.maeharin.kotlindvdrental.domain.model.Film
import com.maeharin.kotlindvdrental.domain.repository.ActorRepository
import com.maeharin.kotlindvdrental.domain.repository.CategoryRepository
import com.maeharin.kotlindvdrental.domain.repository.FilmRepository
import com.maeharin.kotlindvdrental.domain.repository.LanguageRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FilmApplicationService(
    private val filmRepository: FilmRepository,
    private val languageRepository: LanguageRepository,
    private val actorRepository: ActorRepository,
    private val categoryRepository: CategoryRepository
) {
    @Transactional
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

    @Transactional
    fun update(command: FilmCommand) {
        // TODO: 権限チェック

        val language   = languageRepository.findById(command.languageId) ?: throw RuntimeException("language not found")
        val actors     = actorRepository.findByIds(command.actorIds)
        val categories = categoryRepository.findByIds(command.categoryIds)

        val film = Film(command = command, language = language, actors = actors, categories = categories)
        filmRepository.update(film)

        // TODO: mail送信
    }
}
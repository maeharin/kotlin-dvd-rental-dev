package com.maeharin.kotlindvdrental.domain.repository

import com.maeharin.kotlindvdrental.domain.model.Film
import com.maeharin.kotlindvdrental.infrastructure.doma.dao.FilmEntityDao
import org.springframework.stereotype.Repository

@Repository
class FilmRepository(
    private val filmDao: FilmEntityDao,
    private val actorRepository: ActorRepository,
    private val categoryRepository: CategoryRepository
) {
    fun findById(id: Int): Film? {
        return filmDao.selectById(id)?.let {
            val actors = actorRepository.findByFilmId(it.filmId)
            val categories = categoryRepository.findByFilmId(it.filmId)

            Film(entity = it, actors = actors, categories = categories)
        }
    }

    fun findAll(): List<Film> {
        // TODO: N+1対策
        return filmDao.selectAll().map {
            val actors = actorRepository.findByFilmId(it.filmId)
            val categories = categoryRepository.findByFilmId(it.filmId)

            Film(entity = it, actors = actors, categories = categories)
        }
    }
}


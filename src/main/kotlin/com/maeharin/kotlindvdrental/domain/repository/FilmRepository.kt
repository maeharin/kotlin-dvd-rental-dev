package com.maeharin.kotlindvdrental.domain.repository

import com.maeharin.kotlindvdrental.domain.model.Film
import com.maeharin.kotlindvdrental.infrastructure.doma.dao.*
import com.maeharin.kotlindvdrental.infrastructure.doma.entity.FilmActorEntity
import com.maeharin.kotlindvdrental.infrastructure.doma.entity.FilmCategoryEntity
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class FilmRepository(
    private val filmWithRelationDao: FilmWithRelationEntityDao,
    private val filmEntityDao: FilmEntityDao,
    private val languageEntityDao: LanguageEntityDao,
    private val filmActorEntityDao: FilmActorEntityDao,
    private val filmCategoryEntityDao: FilmCategoryEntityDao
) {
    fun findById(id: Int): Film? {
        val entities = filmWithRelationDao.selectById(id)
        return Film.createByFilmWithRelationEntities(entities).first()
    }

    fun findAll(): List<Film> {
        val entities = filmWithRelationDao.selectAll()
        return Film.createByFilmWithRelationEntities(entities)
    }

    // TOOD: 引数はcommandではなく、domain objectであるfilmでは？
    fun store(film: Film): Int {
        // TODO: ビジネスロジックバリデーション

        // save film
        val filmId = filmEntityDao.insert(film.toEntity())

        // save film_actor relations
        film.actors.forEach { actor ->
            filmActorEntityDao.insert(FilmActorEntity().also { e ->
                e.filmId = filmId
                e.actorId = actor.id
                e.lastUpdate = LocalDateTime.now()
            })
        }

        // save film_category relations
        film.categories.forEach { category ->
            filmCategoryEntityDao.insert(FilmCategoryEntity().also { e ->
                e.filmId = filmId
                e.categoryId = category.id
                e.lastUpdate = LocalDateTime.now()
            })
        }

        return filmId
    }
}

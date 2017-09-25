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

    fun store(film: Film): Int {
        // TODO: ビジネスロジックバリデーション

        val entity = film.toEntity()
        println("-------------------------------------")
        println(entity.title)
        println(entity.releaseYear)
        if (entity.releaseYear == null) {
            println("releaseYearはnullです")
        } else {
            println("releaseYearはnullではないです")
        }
        println("-------------------------------------")

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

    fun update(film: Film) {
        //// save film
        //filmEntityDao.update(film.toEntity())

        //// delete all relations. then save new relations
        //filmActorEntityDao.deleteByFilmId(film.id)

        //film.actors.forEach { actor ->
        //    filmActorEntityDao.insert(FilmActorEntity().also { e ->
        //        e.filmId = film.id
        //        e.actorId = actor.id
        //        e.lastUpdate = LocalDateTime.now()
        //    })
        //}

        //filmCategoryEntityDao.deleteByFilmId(film.id)

        //film.categories.forEach { category ->
        //    filmCategoryEntityDao.insert(FilmCategoryEntity().also { e ->
        //        e.filmId = film.id
        //        e.categoryId = category.id
        //        e.lastUpdate = LocalDateTime.now()
        //    })
        //}
    }
}

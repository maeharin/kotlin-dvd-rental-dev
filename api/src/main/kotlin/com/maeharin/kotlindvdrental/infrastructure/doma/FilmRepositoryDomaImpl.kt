package com.maeharin.kotlindvdrental.infrastructure.doma

import com.maeharin.kotlindvdrental.domain.model.Actor
import com.maeharin.kotlindvdrental.domain.model.Category
import com.maeharin.kotlindvdrental.domain.model.Film
import com.maeharin.kotlindvdrental.domain.model.Language
import com.maeharin.kotlindvdrental.domain.repository.FilmRepository
import com.maeharin.kotlindvdrental.infrastructure.doma.dao.FilmActorEntityDao
import com.maeharin.kotlindvdrental.infrastructure.doma.dao.FilmCategoryEntityDao
import com.maeharin.kotlindvdrental.infrastructure.doma.dao.FilmEntityDao
import com.maeharin.kotlindvdrental.infrastructure.doma.dao.FilmWithRelationEntityDao
import com.maeharin.kotlindvdrental.infrastructure.doma.entity.FilmActorEntity
import com.maeharin.kotlindvdrental.infrastructure.doma.entity.FilmCategoryEntity
import com.maeharin.kotlindvdrental.infrastructure.doma.entity.FilmEntity
import com.maeharin.kotlindvdrental.infrastructure.doma.entity.FilmWithRelationEntity
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class FilmRepositoryDomaImpl(
    private val filmWithRelationDao: FilmWithRelationEntityDao,
    private val filmEntityDao: FilmEntityDao,
    private val filmActorEntityDao: FilmActorEntityDao,
    private val filmCategoryEntityDao: FilmCategoryEntityDao
): FilmRepository {
    override fun findById(id: Int): Film? {
        val entities = filmWithRelationDao.selectById(id)

        return if (entities.isEmpty()) {
            null
        } else {
            _toDomainModel(entities).first()
        }
    }

    override fun findAll(): List<Film> {
        val entities = filmWithRelationDao.selectAll()
        return _toDomainModel(entities)
    }

    override fun store(film: Film): Int {
        // TODO: ビジネスロジックバリデーション

        // save film
        val filmEntity = _toDomaEntity(film)

        filmEntityDao.insert(filmEntity)

        val filmId = filmEntity.filmId

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

    override fun update(film: Film) {
        // save film
        filmEntityDao.update(_toDomaEntity(film))

        // delete all relations. then save new relations
        filmActorEntityDao.deleteByFilmId(film.id)

        film.actors.forEach { actor ->
            filmActorEntityDao.insert(FilmActorEntity().also { e ->
                e.filmId = film.id
                e.actorId = actor.id
                e.lastUpdate = LocalDateTime.now()
            })
        }

        filmCategoryEntityDao.deleteByFilmId(film.id)

        film.categories.forEach { category ->
            filmCategoryEntityDao.insert(FilmCategoryEntity().also { e ->
                e.filmId = film.id
                e.categoryId = category.id
                e.lastUpdate = LocalDateTime.now()
            })
        }
    }

    override fun delete(id: Int) {
        // 子テーブルから削除
        filmActorEntityDao.deleteByFilmId(id)
        filmCategoryEntityDao.deleteByFilmId(id)
        // 親テーブル削除
        filmEntityDao.deleteById(id)
    }

    private fun _toDomaEntity(film: Film): FilmEntity {
        return FilmEntity().also { domaEntity ->
            domaEntity.filmId = film.id
            domaEntity.title = film.title
            domaEntity.description = film.description
            domaEntity.releaseYear = film.releaseYear
            domaEntity.rentalDuration = film.rentalDuration
            domaEntity.rentalRate = film.rentalRate
            domaEntity.length = film.length
            domaEntity.replacementCost = film.replacementCost
            domaEntity.languageId = film.language.id
            domaEntity.lastUpdate = LocalDateTime.now()
        }
    }

    private fun _toDomainModel(domaEntities: List<FilmWithRelationEntity>): List<Film> {
        return domaEntities
                .groupBy { it.filmId }
                .values
                .map { filmDomaEntities ->
                    val filmDomaEntity = filmDomaEntities.first()

                    val language = Language(
                            id = filmDomaEntity.languageId,
                            name = filmDomaEntity.languageName,
                            updatedAt = filmDomaEntity.languageLastUpdate
                    )

                    val actors = if (filmDomaEntities.all { it.actorId == null }) {
                        emptyList()
                    } else {
                        filmDomaEntities.distinctBy { it.actorId }.map {
                            Actor(
                                    id = it.actorId,
                                    firstName = it.actorFirstName,
                                    lastName = it.actorLastName,
                                    updatedAt = it.actorLastUpdate
                            )
                        }
                    }

                    val categories = if (filmDomaEntities.all { it.categoryId == null }) {
                        emptyList()
                    } else {
                        filmDomaEntities.distinctBy { it.categoryId }.map {
                            Category(
                                    id = it.categoryId,
                                    name = it.categoryName,
                                    updatedAt = it.categoryLastUpdate
                            )
                        }
                    }

                    Film(
                      id = filmDomaEntity.filmId,
                      title = filmDomaEntity.title,
                      description = filmDomaEntity.description,
                      releaseYear = filmDomaEntity.releaseYear,
                      rentalDuration = filmDomaEntity.rentalDuration,
                      rentalRate = filmDomaEntity.rentalRate,
                      length = filmDomaEntity.length,
                      replacementCost = filmDomaEntity.replacementCost,
                      language = language,
                      actors = actors,
                      categories = categories
                    )
                }
    }
}

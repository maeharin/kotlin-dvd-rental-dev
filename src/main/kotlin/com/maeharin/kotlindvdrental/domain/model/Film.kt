package com.maeharin.kotlindvdrental.domain.model

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.FilmEntity
import com.maeharin.kotlindvdrental.infrastructure.doma.entity.FilmWithRelationEntity
import java.math.BigDecimal

class Film(
    val id: Int,
    val title: String,
    val description: String?,
    val releaseYear: String?,
    val rentalDuration: Short,
    val rentalRate: BigDecimal,
    val length: Short?,
    val replacementCost: BigDecimal,
    val rating: String,
    //val specialFeatures: List<String>,
    //val fulltext: Map<String, String>,
    val language: Language,
    val actors: List<Actor>,
    val categories: List<Category>
) {
    constructor(entity: FilmEntity, language: Language, actors: List<Actor>, categories: List<Category>): this(
        id = entity.filmId,
        title = entity.title,
        description = entity.description,
        releaseYear = entity.releaseYear,
        rentalDuration = entity.rentalDuration,
        rentalRate = entity.rentalRate,
        length = entity.length,
        replacementCost = entity.replacementCost,
        rating = entity.rating,
        language = language,
        actors = actors,
        categories = categories
    )

    companion object {
        fun createByFilmWithRelationEntities(entities: List<FilmWithRelationEntity>): List<Film> {
            return entities
                    .groupBy { it.filmId }
                    .values
                    .map { filmEntities ->
                        val filmEntity = filmEntities.first()

                        Film(
                            entity = filmEntity,
                            language = Language(
                                id = filmEntity.languageId,
                                name = filmEntity.languageName,
                                updatedAt = filmEntity.languageLastUpdate
                            ),
                            actors = filmEntities.distinctBy { it.actorId }.map {
                                Actor(
                                    id = it.actorId,
                                    firstName = it.actorFirstName,
                                    lastName = it.actorLastName,
                                    updatedAt = it.actorLastUpdate
                                )
                            },
                            categories = filmEntities.distinctBy { it.categoryId }.map {
                                Category(
                                    id = it.categoryId,
                                    name = it.categoryName,
                                    updatedAt = it.categoryLastUpdate
                                )
                            }
                        )
                    }
        }

    }
}
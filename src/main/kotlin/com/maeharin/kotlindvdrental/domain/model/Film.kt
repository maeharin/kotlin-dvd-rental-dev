package com.maeharin.kotlindvdrental.domain.model

import com.maeharin.kotlindvdrental.domain.command.FilmCreateCommand
import com.maeharin.kotlindvdrental.infrastructure.doma.entity.FilmEntity
import com.maeharin.kotlindvdrental.infrastructure.doma.entity.FilmWithRelationEntity
import java.math.BigDecimal

class Film(
    val id: Int? = null,
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

    constructor(command: FilmCreateCommand, language: Language, actors: List<Actor>, categories: List<Category>): this(
        title = command.title,
        description = command.description,
        releaseYear = command.releaseYear,
        rentalDuration = command.rentalDuration,
        rentalRate = command.rentalRate,
        length = command.length,
        replacementCost = command.replacementCost,
        rating = command.rating,
        language = language,
        actors = actors,
        categories = categories
    )

    fun toEntity(): FilmEntity {
        return FilmEntity().also { entity ->
            entity.title = title
            entity.description = description
            entity.releaseYear = releaseYear
            entity.rentalDuration = rentalDuration
            entity.rentalRate = rentalRate
            entity.length = length
            entity.replacementCost = replacementCost
            entity.rating = rating
            entity.languageId = language.id
        }
    }


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
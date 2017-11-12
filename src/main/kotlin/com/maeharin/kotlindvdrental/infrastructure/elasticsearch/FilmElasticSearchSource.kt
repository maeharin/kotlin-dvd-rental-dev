package com.maeharin.kotlindvdrental.infrastructure.elasticsearch

import com.maeharin.kotlindvdrental.domain.model.Actor
import com.maeharin.kotlindvdrental.domain.model.Category
import com.maeharin.kotlindvdrental.domain.model.Film
import com.maeharin.kotlindvdrental.domain.model.Language
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * ElasticSearchのSourceを表現するDTO
 * インデックスしたいカラムだけに絞り込む役割を担う
 */

data class FilmElasticSearchSource(
        val id: Int,
        val title: String,
        val description: String?,
        val releaseYear: Int?,
        val rentalDuration: Short,
        val rentalRate: BigDecimal,
        val length: Short?,
        val replacementCost: BigDecimal,
        val language: LanguageElasticSearchSource,
        val actors: List<ActorElasticSearchSource>,
        val categories: List<CategoryElasticSearchSource>
) {
    constructor(film: Film) : this(
        id = film.id!!,
        title = film.title,
        description = film.description,
        releaseYear = film.releaseYear,
        rentalDuration = film.rentalDuration,
        rentalRate = film.rentalRate,
        length = film.length,
        replacementCost = film.replacementCost,
        language = LanguageElasticSearchSource(film.language),
        actors = film.actors.map(::ActorElasticSearchSource),
        categories = film.categories.map(::CategoryElasticSearchSource)
    )
}

data class LanguageElasticSearchSource(
    val id: Int,
    val name: String,
    val updatedAt: LocalDateTime
) {
    constructor(language: Language) : this(
        id = language.id,
        name = language.name,
        updatedAt = language.updatedAt
    )
}

data class ActorElasticSearchSource(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val updatedAt: LocalDateTime
) {
    constructor(actor: Actor) : this(
        id = actor.id,
        firstName = actor.firstName,
        lastName = actor.lastName,
        updatedAt = actor.updatedAt
    )
}

data class CategoryElasticSearchSource(
    val id: Int,
    val name: String,
    val updatedAt: LocalDateTime
) {
    constructor(category: Category): this(
        id = category.id,
        name = category.name,
        updatedAt = category.updatedAt
    )
}

package com.maeharin.kotlindvdrental.application.restcontroller.staff.resource

import com.maeharin.kotlindvdrental.domain.model.Actor
import com.maeharin.kotlindvdrental.domain.model.Category
import com.maeharin.kotlindvdrental.domain.model.Film
import com.maeharin.kotlindvdrental.domain.model.Language
import java.math.BigDecimal
import java.time.LocalDateTime

data class FilmResource(
        val id: Int,
        val title: String,
        val description: String?,
        val releaseYear: Int?,
        val rentalRate: BigDecimal,
        val length: Short?,
        val language: LanguageResource,
        val actors: List<ActorResource>,
        val categories: List<CategoryResource>
) {
    constructor(film: Film) : this(
            id = film.id!!,
            title = film.title,
            description = film.description,
            releaseYear = film.releaseYear,
            rentalRate = film.rentalRate,
            length = film.length,
            language = LanguageResource(film.language),
            actors = film.actors.map(::ActorResource),
            categories = film.categories.map(::CategoryResource)
    )
}

data class LanguageResource(
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

data class ActorResource(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val fullName: String,
    val updatedAt: LocalDateTime
) {
    constructor(actor: Actor) : this(
        id = actor.id,
        firstName = actor.firstName,
        lastName = actor.lastName,
        fullName = actor.fullName,
        updatedAt = actor.updatedAt
    )
}

data class CategoryResource(
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

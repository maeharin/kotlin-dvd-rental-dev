package com.maeharin.kotlindvdrental.application.restcontroller.resource

import com.maeharin.kotlindvdrental.domain.model.Actor
import com.maeharin.kotlindvdrental.domain.model.Category
import com.maeharin.kotlindvdrental.domain.model.Film
import java.time.LocalDateTime

data class FilmResource(
    val id: Int,
    val title: String,
    val actors: List<ActorResource>,
    val categories: List<CategoryResource>
) {
    constructor(film: Film) : this(
        id = film.id,
        title = film.title,
        actors = film.actors.map(::ActorResource),
        categories = film.categories.map(::CategoryResource)
    )
}

data class ActorResource(
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

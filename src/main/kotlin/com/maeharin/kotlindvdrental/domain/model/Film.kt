package com.maeharin.kotlindvdrental.domain.model

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.FilmEntity

class Film(
    val id: Int,
    val title: String,
    val actors: List<Actor>,
    val categories: List<Category>
) {
    constructor(entity: FilmEntity, actors: List<Actor>, categories: List<Category>): this(
        id = entity.filmId,
        title = entity.title,
        actors = actors,
        categories = categories
    )
}
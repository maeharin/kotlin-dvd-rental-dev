package com.maeharin.kotlindvdrental.domain.model

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.FilmEntity

class Film(
    val id: Int,
    val title: String
) {
    constructor(entity: FilmEntity): this(
        id = entity.filmId,
        title = entity.title
    )
}
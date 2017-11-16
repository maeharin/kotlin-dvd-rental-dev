package com.maeharin.kotlindvdrental.domain.repository

import com.maeharin.kotlindvdrental.domain.model.Actor

interface ActorRepository {
    fun findByFilmId(filmId: Int): List<Actor>
    fun findByIds(ids: List<Int>): List<Actor>
    fun search(query: String): List<Actor>
}
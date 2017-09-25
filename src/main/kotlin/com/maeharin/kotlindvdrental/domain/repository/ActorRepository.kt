package com.maeharin.kotlindvdrental.domain.repository

import com.maeharin.kotlindvdrental.domain.model.Actor
import com.maeharin.kotlindvdrental.infrastructure.doma.dao.ActorEntityDao
import org.springframework.stereotype.Repository

@Repository
class ActorRepository(
    private val actorEntityDao: ActorEntityDao
) {
    fun findByFilmId(filmId: Int): List<Actor>
        = actorEntityDao.selectByFilmId(filmId).map(::Actor)

    fun findByIds(ids: List<Int>): List<Actor>
        = actorEntityDao.selectByIds(ids).map(::Actor)
}


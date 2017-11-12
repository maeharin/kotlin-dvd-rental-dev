package com.maeharin.kotlindvdrental.infrastructure.doma

import com.maeharin.kotlindvdrental.domain.model.Actor
import com.maeharin.kotlindvdrental.domain.repository.ActorRepository
import com.maeharin.kotlindvdrental.infrastructure.doma.dao.ActorEntityDao
import org.springframework.stereotype.Repository

@Repository
class ActorRepositoryDomaImpl(
    private val actorEntityDao: ActorEntityDao
): ActorRepository {
    override fun findByFilmId(filmId: Int): List<Actor>
        = actorEntityDao.selectByFilmId(filmId).map(::Actor)

    override fun findByIds(ids: List<Int>): List<Actor>
        = actorEntityDao.selectByIds(ids).map(::Actor)
}


package com.maeharin.kotlindvdrental.infrastructure.doma

import com.maeharin.kotlindvdrental.domain.model.Actor
import com.maeharin.kotlindvdrental.domain.repository.ActorRepository
import com.maeharin.kotlindvdrental.infrastructure.doma.dao.ActorEntityDao
import com.maeharin.kotlindvdrental.infrastructure.doma.entity.ActorEntity
import org.springframework.stereotype.Repository

@Repository
class ActorRepositoryDomaImpl(
    private val actorEntityDao: ActorEntityDao
): ActorRepository {
    override fun findByFilmId(filmId: Int): List<Actor>
            = actorEntityDao.selectByFilmId(filmId).map { _toDomainModel(it) }

    override fun findByIds(ids: List<Int>): List<Actor>
            = actorEntityDao.selectByIds(ids).map { _toDomainModel(it) }

    override fun search(query: String): List<Actor>
            = actorEntityDao.selectByQuery(query).map { _toDomainModel(it) }

    private fun _toDomainModel(domaEntity: ActorEntity): Actor {
        return Actor(
                id = domaEntity.actorId,
                firstName = domaEntity.firstName,
                lastName = domaEntity.lastName,
                updatedAt = domaEntity.lastUpdate
        )
    }
}

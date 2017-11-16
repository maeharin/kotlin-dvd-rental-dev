package com.maeharin.kotlindvdrental.application.applicationservice

import com.maeharin.kotlindvdrental.domain.model.Actor
import com.maeharin.kotlindvdrental.domain.repository.ActorRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ActorApplicationService(
        private val actorRepository: ActorRepository
) {
    fun search(query: String): List<Actor>
            = actorRepository.search(query)
}
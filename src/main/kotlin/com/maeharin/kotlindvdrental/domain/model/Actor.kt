package com.maeharin.kotlindvdrental.domain.model

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.ActorEntity
import java.time.LocalDateTime

class Actor(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val updatedAt: LocalDateTime
) {
    constructor(entity: ActorEntity): this(
        id = entity.actorId,
        firstName = entity.firstName,
        lastName = entity.lastName,
        updatedAt = entity.lastUpdate
    )
}
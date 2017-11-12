package com.maeharin.kotlindvdrental.domain.model

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.ActorEntity
import java.time.LocalDateTime

data class Actor(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val updatedAt: LocalDateTime
)

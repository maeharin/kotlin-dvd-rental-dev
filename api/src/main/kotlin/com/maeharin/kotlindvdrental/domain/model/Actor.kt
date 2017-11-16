package com.maeharin.kotlindvdrental.domain.model

import java.time.LocalDateTime

data class Actor(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val updatedAt: LocalDateTime
) {
    val fullName: String
        get() = "${lastName} ${firstName}"
}

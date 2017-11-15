package com.maeharin.kotlindvdrental.domain.model

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.LanguageEntity
import java.time.LocalDateTime

data class Language(
    val id: Int,
    val name: String,
    val updatedAt: LocalDateTime
)

package com.maeharin.kotlindvdrental.domain.model

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.LanguageEntity
import java.time.LocalDateTime

class Language(
    val id: Int,
    val name: String,
    val updatedAt: LocalDateTime
) {
    constructor(entity: LanguageEntity): this(
        id = entity.languageId,
        name = entity.name,
        updatedAt = entity.lastUpdate
    )
}
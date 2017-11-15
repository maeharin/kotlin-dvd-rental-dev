package com.maeharin.kotlindvdrental.domain.model

import java.math.BigDecimal

data class Film(
    val id: Int? = null,
    val title: String,
    val description: String?,
    val releaseYear: Int?,
    val rentalDuration: Short,
    val rentalRate: BigDecimal,
    val length: Short?,
    val replacementCost: BigDecimal,
    val language: Language,
    val actors: List<Actor>,
    val categories: List<Category>
)

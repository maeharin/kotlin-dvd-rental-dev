package com.maeharin.kotlindvdrental.application.restcontroller.param

import java.math.BigDecimal

data class FilmRestParam(
    var id: Int? = null,
    val title: String,
    val description: String?,
    val releaseYear: Int?,
    val rentalDuration: Short,
    val rentalRate: BigDecimal,
    val length: Short?,
    val replacementCost: BigDecimal,
    val languageId: Int,
    val actorIds: List<Int> = emptyList(),
    val categoryIds: List<Int> = emptyList()
)


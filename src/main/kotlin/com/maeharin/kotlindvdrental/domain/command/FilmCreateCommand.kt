package com.maeharin.kotlindvdrental.domain.command

import com.maeharin.kotlindvdrental.application.restcontroller.param.FilmCreateParam
import java.math.BigDecimal

data class FilmCreateCommand(
    val title: String,
    val description: String?,
    val releaseYear: String?,
    val rentalDuration: Short,
    val rentalRate: BigDecimal,
    val length: Short?,
    val replacementCost: BigDecimal,
    val rating: String,
    val languageId: Int,
    val actorIds: List<Int>,
    val categoryIds: List<Int>
) {
    constructor(param: FilmCreateParam) : this(
        title = param.title,
        description = param.description,
        releaseYear = param.releaseYear,
        rentalDuration = param.rentalDuration,
        rentalRate = param.rentalRate,
        length = param.length,
        replacementCost = param.replacementCost,
        rating = param.rating,
        languageId = param.languageId,
        actorIds = param.actorIds,
        categoryIds = param.categoryIds
    )
}

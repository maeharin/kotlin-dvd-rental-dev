package com.maeharin.kotlindvdrental.domain.command

import com.maeharin.kotlindvdrental.application.restcontroller.param.FilmCreateParam
import java.math.BigDecimal

data class FilmCreateCommand(
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
) {
    constructor(param: FilmCreateParam) : this(
        title = param.title,
        description = param.description,
        releaseYear = param.releaseYear,
        rentalDuration = param.rentalDuration,
        rentalRate = param.rentalRate,
        length = param.length,
        replacementCost = param.replacementCost,
        languageId = param.languageId,
        actorIds = param.actorIds,
        categoryIds = param.categoryIds
    )
}

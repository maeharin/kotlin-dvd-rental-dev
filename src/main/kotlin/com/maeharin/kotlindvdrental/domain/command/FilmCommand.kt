package com.maeharin.kotlindvdrental.domain.command

import com.maeharin.kotlindvdrental.application.restcontroller.param.FilmRestParam
import java.math.BigDecimal

data class FilmCommand(
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
) {
    constructor(restParam: FilmRestParam) : this(
        id = restParam.id,
        title = restParam.title,
        description = restParam.description,
        releaseYear = restParam.releaseYear,
        rentalDuration = restParam.rentalDuration,
        rentalRate = restParam.rentalRate,
        length = restParam.length,
        replacementCost = restParam.replacementCost,
        languageId = restParam.languageId,
        actorIds = restParam.actorIds,
        categoryIds = restParam.categoryIds
    )
}

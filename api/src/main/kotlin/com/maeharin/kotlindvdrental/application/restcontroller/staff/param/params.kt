package com.maeharin.kotlindvdrental.application.restcontroller.staff.param

import org.hibernate.validator.constraints.NotBlank
import java.math.BigDecimal
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Size

data class FilmRestParam(
    @get:NotBlank
    @get:Size(min = 1, max = 50)
    val title: String,

    @get:Size (min = 1, max = 500)
    val description: String?,

    @get:Min(1901)
    @get:Max(2155)
    val releaseYear: Int?,

    val rentalDuration: Short,

    val rentalRate: BigDecimal,

    val length: Short?,

    val replacementCost: BigDecimal,

    val languageId: Int,

    val actorIds: List<Int> = emptyList(),

    val categoryIds: List<Int> = emptyList()
)


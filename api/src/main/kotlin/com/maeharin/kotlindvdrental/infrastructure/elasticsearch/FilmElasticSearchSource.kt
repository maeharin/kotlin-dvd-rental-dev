package com.maeharin.kotlindvdrental.infrastructure.elasticsearch

import com.maeharin.kotlindvdrental.domain.model.Actor
import com.maeharin.kotlindvdrental.domain.model.Category
import com.maeharin.kotlindvdrental.domain.model.Film
import com.maeharin.kotlindvdrental.domain.model.Language
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * ElasticSearchのSourceを表現するDTO
 * インデックスしたいカラムだけに絞り込む役割を担う
 */

data class FilmElasticSearchSource(
        val id: Int,
        val title: String,
        val description: String?,
        val releaseYear: Int?,
        val rentalDuration: Short,
        val rentalRate: BigDecimal,
        val length: Short?,
        val replacementCost: BigDecimal,
        val language: LanguageElasticSearchSource,
        val actors: List<ActorElasticSearchSource>,
        val categories: List<CategoryElasticSearchSource>
)

data class LanguageElasticSearchSource(
    val id: Int,
    val name: String,
    val updatedAt: LocalDateTime
)

data class ActorElasticSearchSource(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val updatedAt: LocalDateTime
)

data class CategoryElasticSearchSource(
    val id: Int,
    val name: String,
    val updatedAt: LocalDateTime
)

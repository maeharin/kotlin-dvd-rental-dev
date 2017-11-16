package com.maeharin.kotlindvdrental.domain.repository

import com.maeharin.kotlindvdrental.domain.model.Category

interface CategoryRepository {
    fun findByFilmId(filmId: Int): List<Category>
    fun findByIds(ids: List<Int>): List<Category>
    fun findAll(): List<Category>
}
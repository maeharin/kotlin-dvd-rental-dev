package com.maeharin.kotlindvdrental.domain.repository

import com.maeharin.kotlindvdrental.domain.model.Actor
import com.maeharin.kotlindvdrental.domain.model.Category
import com.maeharin.kotlindvdrental.infrastructure.doma.dao.CategoryEntityDao
import org.springframework.stereotype.Repository

@Repository
class CategoryRepository(
    private val categoryEntityDao: CategoryEntityDao
) {
    fun findByFilmId(filmId: Int): List<Category>
        = categoryEntityDao.selectByFilmId(filmId).map(::Category)

    fun findByIds(ids: List<Int>): List<Category>
        = categoryEntityDao.selectByIds(ids).map(::Category)
}


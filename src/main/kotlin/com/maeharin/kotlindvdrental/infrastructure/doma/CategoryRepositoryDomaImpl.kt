package com.maeharin.kotlindvdrental.infrastructure.doma

import com.maeharin.kotlindvdrental.domain.model.Category
import com.maeharin.kotlindvdrental.domain.repository.CategoryRepository
import com.maeharin.kotlindvdrental.infrastructure.doma.dao.CategoryEntityDao
import org.springframework.stereotype.Repository

@Repository
class CategoryRepositoryDomaImpl(
    private val categoryEntityDao: CategoryEntityDao
): CategoryRepository {
    override fun findByFilmId(filmId: Int): List<Category>
        = categoryEntityDao.selectByFilmId(filmId).map(::Category)

    override fun findByIds(ids: List<Int>): List<Category>
        = categoryEntityDao.selectByIds(ids).map(::Category)
}


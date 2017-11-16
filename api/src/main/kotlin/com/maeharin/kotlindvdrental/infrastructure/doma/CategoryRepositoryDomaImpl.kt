package com.maeharin.kotlindvdrental.infrastructure.doma

import com.maeharin.kotlindvdrental.domain.model.Category
import com.maeharin.kotlindvdrental.domain.repository.CategoryRepository
import com.maeharin.kotlindvdrental.infrastructure.doma.dao.CategoryEntityDao
import com.maeharin.kotlindvdrental.infrastructure.doma.entity.CategoryEntity
import org.springframework.stereotype.Repository

@Repository
class CategoryRepositoryDomaImpl(
    private val categoryEntityDao: CategoryEntityDao
): CategoryRepository {
    override fun findByFilmId(filmId: Int): List<Category>
            = categoryEntityDao.selectByFilmId(filmId).map { _toDomainModel(it) }

    override fun findByIds(ids: List<Int>): List<Category>
            = categoryEntityDao.selectByIds(ids).map { _toDomainModel(it) }

    override fun findAll(): List<Category>
            = categoryEntityDao.selectAll().map { _toDomainModel(it) }

    private fun _toDomainModel(domaEntity: CategoryEntity): Category {
        return Category(
                id = domaEntity.categoryId,
                name = domaEntity.name,
                updatedAt = domaEntity.lastUpdate
        )
    }
}


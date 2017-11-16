package com.maeharin.kotlindvdrental.infrastructure.doma

import com.maeharin.kotlindvdrental.domain.model.Language
import com.maeharin.kotlindvdrental.domain.repository.LanguageRepository
import com.maeharin.kotlindvdrental.infrastructure.doma.dao.LanguageEntityDao
import com.maeharin.kotlindvdrental.infrastructure.doma.entity.LanguageEntity
import org.springframework.stereotype.Repository

@Repository
class LanguageRepositoryDomaImpl(
    private val languageEntityDao: LanguageEntityDao
): LanguageRepository {
    override fun findById(id: Int): Language?
        = languageEntityDao.selectById(id)?.let { _toDomailModel(it) }

    override fun findAll(): List<Language>
        = languageEntityDao.selectAll().map { _toDomailModel(it) }

    private fun _toDomailModel(domaEntity: LanguageEntity): Language {
        return Language (
                id = domaEntity.languageId,
                name = domaEntity.name,
                updatedAt = domaEntity.lastUpdate
        )
    }
}


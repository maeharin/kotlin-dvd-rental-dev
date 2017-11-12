package com.maeharin.kotlindvdrental.infrastructure.doma

import com.maeharin.kotlindvdrental.domain.model.Language
import com.maeharin.kotlindvdrental.domain.repository.LanguageRepository
import com.maeharin.kotlindvdrental.infrastructure.doma.dao.LanguageEntityDao
import org.springframework.stereotype.Repository

@Repository
class LanguageRepositoryDomaImpl(
    private val languageEntityDao: LanguageEntityDao
): LanguageRepository {
    override fun findById(id: Int): Language?
        = languageEntityDao.selectById(id)?.let { Language(it) }
}


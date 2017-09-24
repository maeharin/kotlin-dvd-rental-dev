package com.maeharin.kotlindvdrental.domain.repository

import com.maeharin.kotlindvdrental.domain.model.Language
import com.maeharin.kotlindvdrental.infrastructure.doma.dao.LanguageEntityDao
import org.springframework.stereotype.Repository

@Repository
class LanguageRepository(
    private val languageEntityDao: LanguageEntityDao
) {
    fun findById(id: Int): Language?
        = languageEntityDao.selectById(id)?.let { Language(it) }
}


package com.maeharin.kotlindvdrental.application.applicationservice

import com.maeharin.kotlindvdrental.domain.model.Category
import com.maeharin.kotlindvdrental.domain.model.Language
import com.maeharin.kotlindvdrental.domain.repository.CategoryRepository
import com.maeharin.kotlindvdrental.domain.repository.LanguageRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MasterDataApplicationService(
        private val languageRepository: LanguageRepository,
        private val categoryRepository: CategoryRepository
) {
    fun findAllLanguages(): List<Language>
            = languageRepository.findAll()

    fun findAllCategories(): List<Category>
            = categoryRepository.findAll()
}
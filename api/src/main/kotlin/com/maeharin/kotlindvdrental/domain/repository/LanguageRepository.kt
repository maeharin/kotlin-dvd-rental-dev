package com.maeharin.kotlindvdrental.domain.repository

import com.maeharin.kotlindvdrental.domain.model.Language

interface LanguageRepository {
    fun findById(id: Int): Language?
    fun findAll(): List<Language>
}
package com.maeharin.kotlindvdrental.domain.repository

import com.maeharin.kotlindvdrental.domain.model.Film
import com.maeharin.kotlindvdrental.infrastructure.doma.dao.FilmDao
import org.springframework.stereotype.Repository

@Repository
class FilmRepository(
    private val filmDao: FilmDao
) {
    fun findAll(): List<Film> {
        return filmDao.selectAll().map(::Film)
    }
}


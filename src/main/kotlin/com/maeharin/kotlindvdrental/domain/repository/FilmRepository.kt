package com.maeharin.kotlindvdrental.domain.repository

import com.maeharin.kotlindvdrental.domain.model.Film
import com.maeharin.kotlindvdrental.infrastructure.doma.dao.FilmEntityDao
import org.springframework.stereotype.Repository

@Repository
class FilmRepository(
    private val filmDao: FilmEntityDao
) {
    fun findById(id: Int): Film? {
        return filmDao.selectById(id)?.let { Film(it) }
    }

    fun findAll(): List<Film> {
        return filmDao.selectAll().map(::Film)
    }
}


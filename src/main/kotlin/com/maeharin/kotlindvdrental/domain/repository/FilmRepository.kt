package com.maeharin.kotlindvdrental.domain.repository

import com.maeharin.kotlindvdrental.domain.model.Film

interface FilmRepository {
    fun findById(id: Int): Film?
    fun findAll(): List<Film>
    fun store(film: Film): Int
    fun update(film: Film)
    fun delete(id: Int)
}
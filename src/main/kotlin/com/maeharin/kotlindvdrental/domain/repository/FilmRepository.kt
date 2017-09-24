package com.maeharin.kotlindvdrental.domain.repository

import com.maeharin.kotlindvdrental.domain.model.Film
import com.maeharin.kotlindvdrental.infrastructure.doma.dao.FilmWithRelationEntityDao
import org.springframework.stereotype.Repository

@Repository
class FilmRepository(
    private val filmWithRelationDao: FilmWithRelationEntityDao,
    private val languageRepository: LanguageRepository,
    private val actorRepository: ActorRepository,
    private val categoryRepository: CategoryRepository
) {
    fun findById(id: Int): Film? {
        val entities = filmWithRelationDao.selectById(id)
        return Film.createByFilmWithRelationEntities(entities).first()
    }

    fun findAll(): List<Film> {
        val entities = filmWithRelationDao.selectAll()
        return Film.createByFilmWithRelationEntities(entities)
    }
}

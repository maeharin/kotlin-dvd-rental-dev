package com.maeharin.kotlindvdrental.application.infrastructure.doma

import com.maeharin.kotlindvdrental.domain.model.Film
import com.maeharin.kotlindvdrental.infrastructure.doma.ActorRepositoryDomaImpl
import com.maeharin.kotlindvdrental.infrastructure.doma.CategoryRepositoryDomaImpl
import com.maeharin.kotlindvdrental.infrastructure.doma.FilmRepositoryDomaImpl
import com.maeharin.kotlindvdrental.infrastructure.doma.LanguageRepositoryDomaImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@RunWith(SpringRunner::class)
@SpringBootTest
@Transactional
class FilmRepositoryDomaImplTest {
    @Autowired lateinit var filmRepo: FilmRepositoryDomaImpl
    @Autowired lateinit var languageRepo: LanguageRepositoryDomaImpl
    @Autowired lateinit var actorRepo: ActorRepositoryDomaImpl
    @Autowired lateinit var categoryRepo: CategoryRepositoryDomaImpl

    @Test
    fun testCreateAndFindById() {
        val language = languageRepo.findById(1)!!
        val actors = actorRepo.findByIds(listOf(1,2))
        val categories = categoryRepo.findByIds(listOf(1,2))

        val film = Film(
                title = "映画タイトル",
                description = "映画説明",
                releaseYear = 2017,
                rentalDuration = 0,
                rentalRate = BigDecimal("1.11"),
                length = 0,
                replacementCost = BigDecimal("1.11"),
                language = language,
                actors = actors,
                categories = categories
        )

        val id = filmRepo.store(film)

        val createdFilm = filmRepo.findById(id)!!

        assertThat(createdFilm.id).isEqualTo(id)
        assertThat(createdFilm.title).isEqualTo(film.title)
        assertThat(createdFilm.description).isEqualTo(film.description)
        assertThat(createdFilm.releaseYear).isEqualTo(film.releaseYear)
        assertThat(createdFilm.rentalDuration).isEqualTo(film.rentalDuration)
        assertThat(createdFilm.rentalRate).isEqualTo(film.rentalRate)
        assertThat(createdFilm.length).isEqualTo(film.length)
        assertThat(createdFilm.replacementCost).isEqualTo(film.replacementCost)
        assertThat(createdFilm.language).isEqualTo(film.language)
        assertThat(createdFilm.actors).isEqualTo(film.actors)
        assertThat(createdFilm.categories).isEqualTo(film.categories)
    }
}
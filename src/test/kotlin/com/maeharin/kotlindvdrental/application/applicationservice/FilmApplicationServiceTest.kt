package com.maeharin.kotlindvdrental.application.applicationservice

import com.maeharin.kotlindvdrental.domain.model.Actor
import com.maeharin.kotlindvdrental.domain.model.Category
import com.maeharin.kotlindvdrental.domain.model.Film
import com.maeharin.kotlindvdrental.domain.model.Language
import com.maeharin.kotlindvdrental.infrastructure.doma.FilmRepositoryDomaImpl
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@SpringBootTest
@Transactional
class FilmApplicationServiceTest {
    @Autowired lateinit var service: FilmApplicationService

    @MockBean
    private lateinit var filmRepositoryDomaImpl: FilmRepositoryDomaImpl

    @Test
    fun testFindAll() {
        val expected = emptyList<Film>()

        Mockito.`when`(filmRepositoryDomaImpl.findAll())
                .thenReturn(expected)

        val films = service.findAll()

        assertEquals(expected, films)
    }

    @Test
    fun testFindById() {
        val id = 1
        val expected = Film(
                id = 1,
                title = "映画タイトル",
                description = "映画説明",
                releaseYear = 2017,
                rentalDuration = 0,
                rentalRate = BigDecimal(0),
                length = 0,
                replacementCost = BigDecimal(0),
                language = Language(id = 1, name = "English", updatedAt = LocalDateTime.now()),
                actors = listOf(
                        Actor(
                                id = 1,
                                firstName = "氏名",
                                lastName = "名前",
                                updatedAt = LocalDateTime.now()
                        )
                ),
                categories = listOf(
                        Category(
                                id = 1,
                                name = "カテゴリー1",
                                updatedAt = LocalDateTime.now()
                        )
                )
        )

        Mockito.`when`(filmRepositoryDomaImpl.findById(id))
                .thenReturn(expected)

        val film = service.findById(id)

        assertEquals(expected, film)
    }
}
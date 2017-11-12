package com.maeharin.kotlindvdrental.integration

import org.junit.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class FilmRestAPIIntegrationTest : IntegrationTestBase() {
    @Test
    fun shouldGetFilmById() {
        val request = get("/api/v1/films/1")

        mockMvc.perform(request)
                .andExpect(status().isOk())
    }

    @Test
    fun notFoundIfIdNotExists() {
        val request = get("/api/v1/films/0")

        mockMvc.perform(request)
                .andExpect(status().isNotFound())
    }
}
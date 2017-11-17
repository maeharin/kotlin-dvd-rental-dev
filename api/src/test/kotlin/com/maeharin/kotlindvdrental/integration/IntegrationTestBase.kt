package com.maeharin.kotlindvdrental.integration

import com.fasterxml.jackson.databind.ObjectMapper
import com.maeharin.kotlindvdrental.KotlinDvdRentalApplication
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringRunner::class)
@Transactional
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = arrayOf(KotlinDvdRentalApplication::class)
)
abstract class IntegrationTestBase {
    @Autowired lateinit var testRestTemplate: TestRestTemplate
    @Autowired lateinit var context: WebApplicationContext
    lateinit var mockMvc: MockMvc

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print())
                .apply<DefaultMockMvcBuilder>(springSecurity())
                .build()
    }

    fun MockHttpServletRequestBuilder.asStaffUser(
            mockMvc: MockMvc,
            userName: String = "staff_1",
            password: String = "test"
    ): MockHttpServletRequestBuilder {
        val token = _getAccessToken(
                userType = "staff",
                clientId = "staff-api-client",
                clientSecret = "fuge",
                mockMvc = mockMvc,
                userName = userName,
                password = password
        )

        this.header("Authorization", "Bearer ${token}")

        return this
    }

    private fun _getAccessToken(
            userType: String,
            clientId: String,
            clientSecret: String,
            mockMvc: MockMvc,
            userName: String,
            password: String
    ):String {
        val request = post("/oauth/token")
                .param("user_type", userType)
                .param("username", userName)
                .param("password", password)
                .param("scope", "read write")
                .param("grant_type", "password")
                .with(httpBasic(clientId, clientSecret))
                .accept(MediaType.APPLICATION_JSON_VALUE)

        val oauthResponseString = mockMvc
                .perform(request)
                .andReturn()
                .response.contentAsString

        val oauthResponse: Map<String, Any> = ObjectMapper()
                .readerFor(Map::class.java)
                .readValue(oauthResponseString)

        return oauthResponse["access_token"] as String
    }
}
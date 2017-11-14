package com.maeharin.kotlindvdrental

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.service.ApiKey
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun apiV1Document(): Docket {
        val apiKey = ApiKey("access-token", "Authorization", "header")

        val securityContext = SecurityContext
                .builder()
                .forPaths(
                        PathSelectors.ant("/api/v1/**")
                )
                .build()

        return Docket(DocumentationType.SWAGGER_2)
                .groupName("api v1")
                .select()
                    .paths(PathSelectors.ant("/api/v1/**"))
                    .build()
                .securityContexts(listOf(securityContext))
                .securitySchemes(listOf(apiKey))
    }

    @Bean
    fun oauthAuthorizationServerDocument(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .groupName("oauth-api")
                .select()
                .paths(PathSelectors.ant("/oauth/**"))
                .build()
    }
}

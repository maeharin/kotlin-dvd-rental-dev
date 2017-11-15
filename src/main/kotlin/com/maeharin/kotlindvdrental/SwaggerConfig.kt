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
    val apiKey = ApiKey("access-token", "Authorization", "header")

    val securityContext = SecurityContext
            .builder()
            .forPaths(
                    PathSelectors.ant("/api/v1/**")
            )
            .build()

    @Bean
    fun customerDocument(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .groupName("customer-api")
                .select()
                    .paths(PathSelectors.ant("/api/v1/customer/**"))
                    .build()
                .securityContexts(listOf(securityContext))
                .securitySchemes(listOf(apiKey))
    }

    @Bean
    fun staffDocument(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .groupName("staff-api")
                .select()
                .paths(PathSelectors.ant("/api/v1/staff/**"))
                .build()
                .securityContexts(listOf(securityContext))
                .securitySchemes(listOf(apiKey))
    }

    @Bean
    fun sysadminDocument(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .groupName("sysadmin-api")
                .select()
                .paths(PathSelectors.ant("/api/v1/sysadmin/**"))
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

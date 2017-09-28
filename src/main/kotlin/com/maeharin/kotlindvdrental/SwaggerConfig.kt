package com.maeharin.kotlindvdrental

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun apiV1Document(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .groupName("api v1")
                .select()
                .paths(PathSelectors.ant("/api/v1/**"))
                .build()
    }
}

package com.maeharin.kotlindvdrental

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ObjectMapperConfig {
    /**
     * ObjectMapperの設定
     */
    @Bean
    fun objectMapper(): ObjectMapper
        = ObjectMapper()
            // JSR310 Date and Time API 変換対応（LocalDateTimeなどのjava8の日付クラスをISO 8601にマッピング）
            // see: https://touk.pl/blog/2016/02/12/formatting-java-time-with-spring-boot-using-json/
            .registerModule(JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            // jacksonをkotlinで使いやすくする
            // see: https://github.com/FasterXML/jackson-module-kotlin
            .registerModule(KotlinModule())
}

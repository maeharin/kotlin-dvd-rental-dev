package com.maeharin.kotlindvdrental

import org.apache.http.HttpHost
import org.hibernate.validator.constraints.NotBlank
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "elasticsearch")
class ElasticSearchConfig {
    @NotBlank
    lateinit var host1: String

    var host2: String? = null

    @NotBlank
    lateinit var port: String

    @NotBlank
    lateinit var protocol: String

    val httpHost1: HttpHost
        get() = HttpHost(host1, port.toInt(), protocol)

    val httpHost2: HttpHost?
        get() = if(host2.isNullOrBlank()) null else HttpHost(host2, port.toInt(), protocol)
}
package com.maeharin.kotlindvdrental

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer

/**
 * リソースサーバー（oauth2のResourceServer、認証・認可で保護されるサーバー）を作成する
 */
@Configuration
@EnableResourceServer // このアノテーションもspring-oauthのもの
class SecurityOauth2ResourceServerConfig : ResourceServerConfigurerAdapter() {
    @Autowired lateinit var authenticationManager: AuthenticationManager

    override fun configure(resources: ResourceServerSecurityConfigurer) {
        resources.resourceId("KOTLIN_DVD_RENTAL_API_RESOURCES")
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/api/v1/**").hasRole("CAN_ACCESS_CUSTOMER_API")
    }
}
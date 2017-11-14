package com.maeharin.kotlindvdrental

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import javax.sql.DataSource

/**
 * 認証・認可サーバー（oauth2のAuthorizationServer、Token生成サーバー）を作成する
 */
@Configuration
@EnableAuthorizationServer // このアノテーションはspring-oauth2のもの
class SecurityOauth2AuthorizationServerConfig : AuthorizationServerConfigurerAdapter() {
    @Autowired lateinit var authenticationManager: AuthenticationManager
    @Autowired lateinit var dataSource: DataSource

    @Bean
    fun tokenStore(): TokenStore
            = JdbcTokenStore(dataSource)

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
    }

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.inMemory()
                .withClient("customer-api-client")
                .secret("hoge")
                .authorizedGrantTypes("password")
                .scopes("read", "write")
                .resourceIds("KOTLIN_DVD_RENTAL_API_RESOURCES")
    }
}
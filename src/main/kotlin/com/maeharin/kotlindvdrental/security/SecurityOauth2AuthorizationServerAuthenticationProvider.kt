package com.maeharin.kotlindvdrental.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class SecurityOauth2AuthorizationServerAuthenticationProvider : AbstractUserDetailsAuthenticationProvider() {
    @Autowired lateinit var userDetailsService: SecurityOauth2UserDetailsService

    override fun retrieveUser(
            username: String,
            authentication: UsernamePasswordAuthenticationToken
    ): UserDetails {
        if (username.isBlank() || username == "NOT_PROVIDED") {
            throw BadCredentialsException("username required")
        }

        val details = authentication.details

        val userType = if (details is Map<*,*>) details["user_type"] else throw Exception("details must be Map")

        return when (userType) {
            "customer" -> userDetailsService.findCustomerUserDetailsByLoginId(username)
            "staff" -> userDetailsService.findStaffUserDetailsByLoginId(username)
            else -> throw Exception("user_type must be customer or staff")
        }
    }

    override fun additionalAuthenticationChecks(
            userDetails: UserDetails,
            authentication: UsernamePasswordAuthenticationToken
    ) {
        val presentedPassword = authentication.credentials?.toString() ?: ""

        if (presentedPassword.isBlank()) {
            throw BadCredentialsException("password required")
        }

        val passwordEncoder = BCryptPasswordEncoder()

        if (!passwordEncoder.matches(presentedPassword, userDetails.password)) {
            throw BadCredentialsException("password unmatched")
        }
    }
}
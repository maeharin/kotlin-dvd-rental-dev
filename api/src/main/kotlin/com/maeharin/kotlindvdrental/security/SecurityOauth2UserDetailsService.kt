package com.maeharin.kotlindvdrental.security

import com.maeharin.kotlindvdrental.domain.repository.CustomerRepository
import com.maeharin.kotlindvdrental.domain.repository.StaffRepository
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class SecurityOauth2UserDetailsService(
        private val customerRepository: CustomerRepository,
        private val staffRepository: StaffRepository
) {
    fun findCustomerUserDetailsByLoginId(loginId: String): UserDetails {
        val customer = customerRepository.findByLoginId(loginId) ?: throw BadCredentialsException("not found")

        val authorities = AuthorityUtils.createAuthorityList("ROLE_CAN_ACCESS_CUSTOMER_API")

        return CustomerUserDetails(
                username = customer.loginId,
                password = customer.passwordDigest,
                authorities = authorities
        )
    }

    fun findStaffUserDetailsByLoginId(loginId: String): UserDetails {
        val staff = staffRepository.findByLoginId(loginId) ?: throw BadCredentialsException("not found")

        val authorities = AuthorityUtils.createAuthorityList("ROLE_CAN_ACCESS_STAFF_API")

        return StaffUserDetails(
                username = staff.loginId,
                password = staff.passwordDigest,
                authorities = authorities
        )
    }
}

class CustomerUserDetails(
        username: String,
        password: String,
        authorities: Collection<GrantedAuthority>,
        userType: String = "customer"
): User(username, password, authorities) {
    val userType = userType
}

class StaffUserDetails(
        username: String,
        password: String,
        authorities: Collection<GrantedAuthority>,
        userType: String = "staff"
): User(username, password, authorities) {
    val userType = userType
}

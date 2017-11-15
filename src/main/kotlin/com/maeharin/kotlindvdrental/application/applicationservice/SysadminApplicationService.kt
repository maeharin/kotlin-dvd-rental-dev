package com.maeharin.kotlindvdrental.application.applicationservice

import com.maeharin.kotlindvdrental.domain.repository.CustomerRepository
import com.maeharin.kotlindvdrental.domain.repository.StaffRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SysadminApplicationService(
        private val staffRepository: StaffRepository,
        private val customerRepository: CustomerRepository
) {
    fun initAllLoginIdAndPassword() {
        val passwordEncoder = BCryptPasswordEncoder()

        staffRepository.initAllLoginIdAndPassword(
                loginIdPrefix = "staff_",
                passwordDigest = passwordEncoder.encode("test")
        )

        customerRepository.initAllLoginIdAndPassword(
                loginIdPrefix = "customer_",
                passwordDigest = passwordEncoder.encode("test")
        )
    }
}

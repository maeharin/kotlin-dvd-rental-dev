package com.maeharin.kotlindvdrental.domain.model

import java.time.LocalDateTime

data class Staff(
        val id: Int? = null,
        val loginId: String,
        val passwordDigest: String,
        val firstName: String,
        val lastName: String,
        val email: String?,
        val updatedAt: LocalDateTime,
        val isActive: Boolean,
        val addressId: Short,
        val storeId: Short
)
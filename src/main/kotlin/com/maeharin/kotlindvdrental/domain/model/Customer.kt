package com.maeharin.kotlindvdrental.domain.model

import java.time.LocalDate
import java.time.LocalDateTime

data class Customer(
    val id: Int? = null,
    val loginId: String,
    val passwordDigest: String,
    val firstName: String,
    val lastName: String,
    val email: String?,
    val createdDate: LocalDate,
    val updatedAt: LocalDateTime,
    val isActive: Boolean,
    val addressId: Short,
    val storeId: Short
)
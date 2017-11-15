package com.maeharin.kotlindvdrental.domain.repository

import com.maeharin.kotlindvdrental.domain.model.Customer

interface CustomerRepository {
    fun findByLoginId(loginId: String): Customer?
    fun initAllLoginIdAndPassword(loginIdPrefix: String, passwordDigest: String)
}
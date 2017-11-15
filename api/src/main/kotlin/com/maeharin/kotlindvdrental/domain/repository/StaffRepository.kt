package com.maeharin.kotlindvdrental.domain.repository

import com.maeharin.kotlindvdrental.domain.model.Staff

interface StaffRepository {
    fun findByLoginId(loginId: String): Staff?
    fun save(staff: Staff): Int
    fun initAllLoginIdAndPassword(loginIdPrefix: String, passwordDigest: String)
}
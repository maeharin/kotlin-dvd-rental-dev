package com.maeharin.kotlindvdrental.infrastructure.doma

import com.maeharin.kotlindvdrental.domain.model.Staff
import com.maeharin.kotlindvdrental.domain.repository.StaffRepository
import com.maeharin.kotlindvdrental.infrastructure.doma.dao.StaffEntityDao
import com.maeharin.kotlindvdrental.infrastructure.doma.entity.StaffEntity
import org.springframework.stereotype.Repository

@Repository
class StaffRepositoryDomaImpl(
    private val staffEntityDao: StaffEntityDao
): StaffRepository {
    override fun findByLoginId(loginId: String): Staff?
            = staffEntityDao.selectByLoginId(loginId)?.let { _toDomainModel(it) }

    override fun save(staff: Staff): Int {
        val staffDomaEntity = _toDomaEntity(staff)
        staffEntityDao.insert(staffDomaEntity)
        return staffDomaEntity.staffId
    }

    override fun initAllLoginIdAndPassword(
            loginIdPrefix: String,
            passwordDigest: String
    ) {
        staffEntityDao.updateAllLoginIdAndPassword(loginIdPrefix, passwordDigest)
    }

    private fun _toDomainModel(domaEntity: StaffEntity): Staff {
        return Staff(
                id = domaEntity.staffId,
                loginId = domaEntity.loginId,
                passwordDigest = domaEntity.passwordDigest,
                firstName = domaEntity.firstName,
                lastName = domaEntity.lastName,
                email = domaEntity.email,
                isActive = domaEntity.active,
                updatedAt = domaEntity.lastUpdate,
                storeId = domaEntity.storeId,
                addressId = domaEntity.addressId
        )
    }

    private fun _toDomaEntity(staff: Staff): StaffEntity {
        return StaffEntity().let { domaEntity ->
            domaEntity.staffId = staff.id
            domaEntity.loginId = staff.loginId
            domaEntity.passwordDigest = staff.passwordDigest
            domaEntity.firstName = staff.firstName
            domaEntity.lastName = staff.lastName
            domaEntity.email = staff.email
            domaEntity.active = staff.isActive
            domaEntity.lastUpdate = staff.updatedAt
            domaEntity.storeId = staff.storeId
            domaEntity.addressId = staff.addressId
            domaEntity
        }
    }
}
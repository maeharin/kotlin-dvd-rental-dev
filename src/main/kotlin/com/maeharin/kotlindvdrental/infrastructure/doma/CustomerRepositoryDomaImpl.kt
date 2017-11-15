package com.maeharin.kotlindvdrental.infrastructure.doma

import com.maeharin.kotlindvdrental.domain.model.Customer
import com.maeharin.kotlindvdrental.domain.repository.CustomerRepository
import com.maeharin.kotlindvdrental.infrastructure.doma.dao.CustomerEntityDao
import com.maeharin.kotlindvdrental.infrastructure.doma.entity.CustomerEntity
import org.springframework.stereotype.Repository

@Repository
class CustomerRepositoryDomaImpl(
    private val customerEntityDao: CustomerEntityDao
): CustomerRepository {
    override fun findByLoginId(loginId: String): Customer?
            = customerEntityDao.selectByLoginId(loginId)?.let { _toDomainModel(it) }

    override fun initAllLoginIdAndPassword(
            loginIdPrefix: String,
            passwordDigest: String
    ) {
        customerEntityDao.updateAllLoginIdAndPassword(loginIdPrefix, passwordDigest)
    }

    private fun _toDomainModel(domaEntity: CustomerEntity): Customer {
        return Customer(
                id = domaEntity.customerId,
                loginId = domaEntity.loginId,
                passwordDigest = domaEntity.passwordDigest,
                firstName = domaEntity.firstName,
                lastName = domaEntity.lastName,
                email = domaEntity.email,
                isActive = domaEntity.activebool,
                createdDate = domaEntity.createDate,
                updatedAt = domaEntity.lastUpdate,
                storeId = domaEntity.storeId,
                addressId = domaEntity.addressId
        )
    }
}
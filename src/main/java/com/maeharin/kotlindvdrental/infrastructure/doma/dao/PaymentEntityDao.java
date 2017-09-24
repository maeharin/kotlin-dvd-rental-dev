package com.maeharin.kotlindvdrental.infrastructure.doma.dao;

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.PaymentEntity;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import java.util.List;


/**
 */
@ConfigAutowireable
@Dao
public interface PaymentEntityDao {

    /**
     * @param paymentId
     * @return the PaymentEntity entity
     */
    @Select
    PaymentEntity selectById(Integer paymentId);

    /**
     * @return the PaymentEntity entity list
     */
    @Select
    List<PaymentEntity> selectAll();

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(PaymentEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(PaymentEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(PaymentEntity entity);
}
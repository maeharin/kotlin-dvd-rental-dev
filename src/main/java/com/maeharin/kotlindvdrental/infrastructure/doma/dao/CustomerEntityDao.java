package com.maeharin.kotlindvdrental.infrastructure.doma.dao;

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.CustomerEntity;
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
public interface CustomerEntityDao {

    /**
     * @param customerId
     * @return the CustomerEntity entity
     */
    @Select
    CustomerEntity selectById(Integer customerId);

    @Select
    CustomerEntity selectByLoginId(String loginId);

    /**
     * @return the CustomerEntity entity list
     */
    @Select
    List<CustomerEntity> selectAll();

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(CustomerEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(CustomerEntity entity);

    @Update(sqlFile = true)
    int updateAllLoginIdAndPassword(String loginIdPrefix, String passwordDigest);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(CustomerEntity entity);
}
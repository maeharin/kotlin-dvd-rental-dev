package com.maeharin.kotlindvdrental.infrastructure.doma.dao;

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.AddressEntity;
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
public interface AddressEntityDao {

    /**
     * @param addressId
     * @return the AddressEntity entity
     */
    @Select
    AddressEntity selectById(Integer addressId);

    /**
     * @return the AddressEntity entity list
     */
    @Select
    List<AddressEntity> selectAll();

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(AddressEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(AddressEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(AddressEntity entity);
}
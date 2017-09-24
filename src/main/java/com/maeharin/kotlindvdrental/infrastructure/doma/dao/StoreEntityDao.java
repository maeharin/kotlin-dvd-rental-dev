package com.maeharin.kotlindvdrental.infrastructure.doma.dao;

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.StoreEntity;
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
public interface StoreEntityDao {

    /**
     * @param storeId
     * @return the StoreEntity entity
     */
    @Select
    StoreEntity selectById(Integer storeId);

    /**
     * @return the StoreEntity entity list
     */
    @Select
    List<StoreEntity> selectAll();

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(StoreEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(StoreEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(StoreEntity entity);
}
package com.maeharin.kotlindvdrental.infrastructure.doma.dao;

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.InventoryEntity;
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
public interface InventoryEntityDao {

    /**
     * @param inventoryId
     * @return the InventoryEntity entity
     */
    @Select
    InventoryEntity selectById(Integer inventoryId);

    /**
     * @return the InventoryEntity entity list
     */
    @Select
    List<InventoryEntity> selectAll();

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(InventoryEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(InventoryEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(InventoryEntity entity);
}
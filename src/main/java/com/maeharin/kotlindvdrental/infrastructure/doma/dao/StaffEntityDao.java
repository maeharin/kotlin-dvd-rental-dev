package com.maeharin.kotlindvdrental.infrastructure.doma.dao;

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.StaffEntity;
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
public interface StaffEntityDao {

    /**
     * @param staffId
     * @return the StaffEntity entity
     */
    @Select
    StaffEntity selectById(Integer staffId);

    /**
     * @return the StaffEntity entity list
     */
    @Select
    List<StaffEntity> selectAll();

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(StaffEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(StaffEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(StaffEntity entity);
}
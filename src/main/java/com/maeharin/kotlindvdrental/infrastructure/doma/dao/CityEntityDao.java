package com.maeharin.kotlindvdrental.infrastructure.doma.dao;

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.CityEntity;
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
public interface CityEntityDao {

    /**
     * @param cityId
     * @return the CityEntity entity
     */
    @Select
    CityEntity selectById(Integer cityId);

    /**
     * @return the CityEntity entity list
     */
    @Select
    List<CityEntity> selectAll();

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(CityEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(CityEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(CityEntity entity);
}
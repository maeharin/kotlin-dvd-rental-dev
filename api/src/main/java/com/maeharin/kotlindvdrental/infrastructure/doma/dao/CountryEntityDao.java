package com.maeharin.kotlindvdrental.infrastructure.doma.dao;

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.CountryEntity;
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
public interface CountryEntityDao {

    /**
     * @param countryId
     * @return the CountryEntity entity
     */
    @Select
    CountryEntity selectById(Integer countryId);

    /**
     * @return the CountryEntity entity list
     */
    @Select
    List<CountryEntity> selectAll();

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(CountryEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(CountryEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(CountryEntity entity);
}
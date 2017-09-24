package com.maeharin.kotlindvdrental.infrastructure.doma.dao;

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.RentalEntity;
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
public interface RentalEntityDao {

    /**
     * @param rentalId
     * @return the RentalEntity entity
     */
    @Select
    RentalEntity selectById(Integer rentalId);

    /**
     * @return the RentalEntity entity list
     */
    @Select
    List<RentalEntity> selectAll();

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(RentalEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(RentalEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(RentalEntity entity);
}
package com.maeharin.kotlindvdrental.infrastructure.doma.dao;

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.FilmEntity;
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
public interface FilmEntityDao {

    /**
     * @param filmId
     * @return the FilmEntity entity
     */
    @Select
    FilmEntity selectById(Integer filmId);

    /**
     * @return the FilmEntity entity list
     */
    @Select
    List<FilmEntity> selectAll();

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(FilmEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(FilmEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(FilmEntity entity);
}
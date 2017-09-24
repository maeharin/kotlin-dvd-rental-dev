package com.maeharin.kotlindvdrental.infrastructure.doma.dao;

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.FilmCategoryEntity;
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
public interface FilmCategoryEntityDao {

    /**
     * @param filmId
     * @param categoryId
     * @return the FilmCategoryEntity entity
     */
    @Select
    FilmCategoryEntity selectById(Short filmId, Short categoryId);

    /**
     * @return the FilmCategoryEntity entity list
     */
    @Select
    List<FilmCategoryEntity> selectAll();

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(FilmCategoryEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(FilmCategoryEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(FilmCategoryEntity entity);
}
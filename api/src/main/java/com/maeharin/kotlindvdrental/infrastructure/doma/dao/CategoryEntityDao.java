package com.maeharin.kotlindvdrental.infrastructure.doma.dao;

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.CategoryEntity;
import org.jetbrains.annotations.NotNull;
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
public interface CategoryEntityDao {

    /**
     * @param categoryId
     * @return the CategoryEntity entity
     */
    @Select
    CategoryEntity selectById(Integer categoryId);

    /**
     * @return the CategoryEntity entity list
     */
    @Select
    List<CategoryEntity> selectAll();


    @Select
    List<CategoryEntity> selectByFilmId(Integer filmId);

    @Select
    List<CategoryEntity> selectByIds(List<Integer> categoryIds);

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(CategoryEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(CategoryEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(CategoryEntity entity);
}
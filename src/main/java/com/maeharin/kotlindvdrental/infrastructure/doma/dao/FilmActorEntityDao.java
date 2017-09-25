package com.maeharin.kotlindvdrental.infrastructure.doma.dao;

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.FilmActorEntity;
import org.jetbrains.annotations.Nullable;
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
public interface FilmActorEntityDao {

    /**
     * @param actorId
     * @param filmId
     * @return the FilmActorEntity entity
     */
    @Select
    FilmActorEntity selectById(Short actorId, Short filmId);

    /**
     * @return the FilmActorEntity entity list
     */
    @Select
    List<FilmActorEntity> selectAll();

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(FilmActorEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(FilmActorEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(FilmActorEntity entity);

    @Delete(sqlFile = true)
    int deleteByFilmId(Integer filmId);
}
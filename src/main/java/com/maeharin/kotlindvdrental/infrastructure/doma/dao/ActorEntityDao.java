package com.maeharin.kotlindvdrental.infrastructure.doma.dao;

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.ActorEntity;
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
public interface ActorEntityDao {

    /**
     * @param actorId
     * @return the ActorEntity entity
     */
    @Select
    ActorEntity selectById(Integer actorId);

    /**
     * @return the ActorEntity entity list
     */
    @Select
    List<ActorEntity> selectAll();

    @Select
    List<ActorEntity> selectByFilmId(Integer filmId);

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(ActorEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(ActorEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(ActorEntity entity);
}
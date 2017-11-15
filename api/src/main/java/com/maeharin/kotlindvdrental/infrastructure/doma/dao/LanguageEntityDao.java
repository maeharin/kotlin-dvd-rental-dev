package com.maeharin.kotlindvdrental.infrastructure.doma.dao;

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.LanguageEntity;
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
public interface LanguageEntityDao {

    /**
     * @param languageId
     * @return the LanguageEntity entity
     */
    @Select
    LanguageEntity selectById(Integer languageId);

    /**
     * @return the LanguageEntity entity list
     */
    @Select
    List<LanguageEntity> selectAll();

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(LanguageEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(LanguageEntity entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(LanguageEntity entity);
}
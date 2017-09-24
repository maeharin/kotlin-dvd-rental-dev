package com.maeharin.kotlindvdrental.infrastructure.doma.dao;

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.FilmWithRelationEntity;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface FilmWithRelationEntityDao {
    @Select
    List<FilmWithRelationEntity> selectById(int filmId);

    @Select
    List<FilmWithRelationEntity> selectAll();
}

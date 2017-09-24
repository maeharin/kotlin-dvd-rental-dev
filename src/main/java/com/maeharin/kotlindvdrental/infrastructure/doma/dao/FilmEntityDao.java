package com.maeharin.kotlindvdrental.infrastructure.doma.dao;

import com.maeharin.kotlindvdrental.infrastructure.doma.entity.FilmEntity;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface FilmEntityDao {
    @Select
    FilmEntity selectById(int filmId);

    @Select
    List<FilmEntity> selectAll();
}
